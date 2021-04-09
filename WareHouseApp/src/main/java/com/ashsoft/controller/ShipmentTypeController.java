package com.ashsoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashsoft.model.ShipmentType;
import com.ashsoft.service.IShipmentTypeService;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController {

	@Autowired
	private IShipmentTypeService service;

	// 1. Show Register page
	@GetMapping("/register")
	public String showReg() {
		return "ShipmentTypeRegister";
	}

	// 2. Saving Data

	@PostMapping("/save")
	public String saveShipmentType(@ModelAttribute ShipmentType shipmentType, Model model) {

		Integer id = service.saveShipmentType(shipmentType);

		String message = "ShipmentType with " + id + " Saved Successfully..";
		model.addAttribute("msg", message);

		return "ShipmentTypeRegister";
	}

	// 3. Getting Data

	@GetMapping("/all")
	//URL like localhost:9090/all?page=0
	public String viewAllShipmentsByPage( @PageableDefault(page=0, size=3) Pageable pageable,Model model)
	{
		Page<ShipmentType> page = service.getAllShipmentsByPage(pageable);
		model.addAttribute("page", page);
		return "ShipmentTypeData";
	}
	/* public String displayShipmentType(Model model) {
		List<ShipmentType> list = service.getAllShipmentTypes();

		model.addAttribute("list", list);

		return "ShipmentTypeData";

	} */

	// 4. Deleting Data

	@GetMapping("/delete")
	public String deleteShipmentType(@RequestParam("id") Integer id, Model model, @PageableDefault(page=0, size=3) Pageable p) {

		if (service.shipmentIfExist(id)) // if id exists
		{
			// call service
			service.deleteShipmentType(id);

			// String message="Shipment Type "+id+" deleted Successfully...";

			String message = new StringBuffer().append("Shipment Type '").append(id).append("' Deleted Successfully...")
					.toString();

			// send to UI
			model.addAttribute("msg", message);
		} else {
			model.addAttribute("msg", id + " not found!!");
		}

		// Refreshed List
		//model.addAttribute("list", service.getAllShipmentTypes());
		model.addAttribute("page", service.getAllShipmentsByPage(p));

		return "ShipmentTypeData";
	}

	// 5. Editing Data
	@GetMapping("/edit")
	public String editShipmentType(@RequestParam("id") Integer sid, Model model) {

		// call service layer
		Optional<ShipmentType> opt = service.getOneShipmentType(sid);

		String page = null;

		if (opt.isPresent()) // if data is present
		{
			// object --> Fill in Form
			model.addAttribute("shipmentType", opt.get());

			page = "ShipmentTypeEdit";
		} else {
			// response.sendRedirect("/all");
			page = "redirect:all";
		}

		return page;
	}

	// 6. Updating Data

	@PostMapping("/update")
	public String updateShipmentType(@ModelAttribute ShipmentType shipmentType, Model model) {

		// call service layer
		service.updateShipmentType(shipmentType);

		// Sending message to UI
		model.addAttribute("msg", "ShipmentType with ID '" + shipmentType.getId() + "' Updated Successfully..");

		// Sending Fresh data to UI

		model.addAttribute("list", service.getAllShipmentTypes());

		// going back to UI page
		return "ShipmentTypeData";
	}
}
