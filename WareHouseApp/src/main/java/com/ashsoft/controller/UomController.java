package com.ashsoft.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashsoft.model.Uom;
import com.ashsoft.service.IUomService;

@Controller
@RequestMapping("/uom")
public class UomController {

	@Autowired
	private IUomService service;

	// 1. Show Register page
	@GetMapping("/register")
	public String showReg() {
		return "UomRegister";
	}

	// 2. Saving Data

	@PostMapping("/save")
	public String saveUom(@ModelAttribute Uom uom, Model model) {

		Integer id = service.saveUom(uom);

		String message = "Uom with " + id + " Saved Successfully..";
		model.addAttribute("msg", message);

		return "UomRegister";
	}

	// 3. Getting Data

	@GetMapping("/all")

	public String displayUom(Model model) {
		List<Uom> list = service.getAllUoms();

		model.addAttribute("list", list);

		return "UomData";

	}

	// 4. Deleting Data

	@GetMapping("/delete")
	public String deleteUom(@RequestParam("id") Integer id, Model model) {

		if (service.uomIfExist(id)) // if id exists
		{
			// call service
			service.deleteUom(id);

			// String message="Uom with "+id+" deleted Successfully...";

			String message = new StringBuffer().append("Uom with '").append(id).append("' Deleted Successfully...")
					.toString();

			// send to UI
			model.addAttribute("msg", message);
		} else {
			model.addAttribute("msg", id + " not found!!");
		}

		// Refreshed List
		model.addAttribute("list", service.getAllUoms());

		return "UomData";
	}

	// 5. Editing Data
	@GetMapping("/edit")
	public String editUom(@RequestParam("id") Integer uid, Model model) {

		// call service layer
		Optional<Uom> opt = service.getOneUom(uid);

		String page = null;

		if (opt.isPresent()) // if data is present
		{
			// object --> Fill in Form
			model.addAttribute("uom", opt.get());

			page = "UomEdit";
		} else {
			// response.sendRedirect("/all");
			page = "redirect:all";
		}

		return page;
	}

	// 6. Updating Data

	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom, Model model) {

		// call service layer
		service.updateUom(uom);

		// Sending message to UI
		model.addAttribute("msg", "Uom with ID '" + uom.getId() + "' Updated Successfully..");

		// Sending Fresh data to UI

		model.addAttribute("list", service.getAllUoms());

		// going back to UI page
		return "UomData";
	}
}
