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

import com.ashsoft.model.Part;
import com.ashsoft.service.IOrderMethodService;
import com.ashsoft.service.IPartService;
import com.ashsoft.service.IUomService;

@Controller
@RequestMapping("/part")
public class PartController {

	@Autowired
	private IPartService service;
	
	@Autowired
	private IUomService uomService;
	
	@Autowired
	private IOrderMethodService omService;
	
	// 1. Show Register page
	@GetMapping("/register")
	public String showReg(Model model) {
		//dropdown of Uom
		createCommonChildUI(model);
		return "PartRegister";
	}
	
	 //Method to show Drop-downs(Integrations)
	 private void createCommonChildUI(Model model)
	 {
		 model.addAttribute("uoms", uomService.getUomIdAndModel());
		 model.addAttribute("omSales", omService.getOrderMethodIdAndCode("Sale"));
		 model.addAttribute("omPurchases",omService.getOrderMethodIdAndCode("Purchase"));
	 }

	// 2. Saving Data

	@PostMapping("/save")
	public String savePart(@ModelAttribute Part part, Model model) {

		Integer id = service.savePart(part);

		String message = "Part with " + id + " Saved Successfully..";
		model.addAttribute("msg", message);
		
		createCommonChildUI(model);

		return "PartRegister";
	}

	// 3. Getting Data

	@GetMapping("/all")

	public String displayPart(Model model) {
		List<Part> list = service.getAllParts();

		model.addAttribute("list", list);

		return "PartData";

	}

	// 4. Deleting Data

	@GetMapping("/delete")
	public String deletePart(@RequestParam("id") Integer id, Model model) {

		if (service.partIfExist(id)) // if id exists
		{
			// call service
			service.deletePart(id);;

			// String message="Part with "+id+" deleted Successfully...";

			String message = new StringBuffer().append("Part with '").append(id).append("' Deleted Successfully...")
					.toString();

			// send to UI
			model.addAttribute("msg", message);
		} else {
			model.addAttribute("msg", id + " not found!!");
		}

		// Refreshed List
		model.addAttribute("list", service.getAllParts());

		return "PartData";
	}

	// 5. Editing Data
	@GetMapping("/edit")
	public String editPart(@RequestParam("id") Integer pid, Model model) {

		// call service layer
		Optional<Part> opt = service.getOnePart(pid);

		String page = null;

		if (opt.isPresent()) // if data is present
		{
			// object --> Fill in Form
			model.addAttribute("part", opt.get());

			page = "PartEdit";
		} else {
			// response.sendRedirect("/all");
			page = "redirect:all";
		}
		createCommonChildUI(model);

		return page;
	}

	// 6. Updating Data

	@PostMapping("/update")
	public String updatePart(@ModelAttribute Part part, Model model) {

		// call service layer
		service.updatePart(part);

		// Sending message to UI
		model.addAttribute("msg", "Part with ID '" + part.getId() + "' Updated Successfully..");

		// Sending Fresh data to UI

		model.addAttribute("list", service.getAllParts());

		// going back to UI page
		return "PartData";
	}
}
