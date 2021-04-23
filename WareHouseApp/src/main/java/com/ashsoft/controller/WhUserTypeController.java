package com.ashsoft.controller;

import java.util.Arrays;
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
import org.springframework.web.servlet.ModelAndView;

import com.ashsoft.model.WhUserType;
import com.ashsoft.service.IWhUserTypeService;
import com.ashsoft.view.WhUserTypeExcelView;
import com.ashsoft.view.WhUserTypePdfView;

@Controller
@RequestMapping("/wh")
public class WhUserTypeController {
	
	@Autowired
	private IWhUserTypeService service;
	
	//1. Show Registration Page
	
	@GetMapping("/register")
	public String regWhUserType()
	{
		
		return "WhUserTypeRegister";
	}
	
	//2. Saving Data
	@PostMapping("/save")
	public String saveWhUserType(@ModelAttribute WhUserType whUserType,Model model) {
		Integer id = service.saveWhUserType(whUserType);
		
		String msg="WareHouse User with ID '"+id+"' Saved Successfully...";
		model.addAttribute("msg", msg);
		
		return "WhUserTypeRegister";
	}
	
	//3. Displaying Data in Table
	@GetMapping("/all")
	public String getAllWhUserTypes(Model model)
	{
	    //call service
		List<WhUserType> list = service.getAllWhUserTypes();
		
		// send to UI
		model.addAttribute("list", list);

		
		return "WhUserTypeData";
	}
	//4. Deleting Data
	
	@GetMapping("/delete")
	public String deleteWhUserType(@RequestParam("id") Integer id, Model model) {
		
		if(service.whUserTypeIfExist(id))
		{
			//call service
			service.deleteWhUserType(id);
			model.addAttribute("msg", "User With ID: "+id+" Deleted Successfully..");
		}
		else
		{
			model.addAttribute("msg", "User Not Found..!!");
		}
		// Refresh List and send to UI
		model.addAttribute("list", service.getAllWhUserTypes());
		return "WhUserTypeData";
	}
	// 5. Editing Data
	@GetMapping("/edit")
	public String editWhUserType(@RequestParam("id") Integer wid, Model model) {

		// call service layer
		Optional<WhUserType> opt = service.getOneWhUserType(wid);

		String page = null;

		if (opt.isPresent()) // if data is present
		{
			// object --> Fill in Form
			model.addAttribute("whuserType", opt.get());

			page = "WhUserTypeEdit";
		} else {
			// response.sendRedirect("/all");
			page = "redirect:all";
		}

		return page;
	}

	// 6. Updating Data

	@PostMapping("/update")
	public String updateWhUserType(@ModelAttribute WhUserType whUserType, Model model) {

		// call service layer
		service.updateWhUserType(whUserType);

		// Sending message to UI
		model.addAttribute("msg", "Warehouse User with ID '" + whUserType.getId() + "' Updated Successfully..");

		// Sending Fresh data to UI

		model.addAttribute("list", service.getAllWhUserTypes());

		// going back to UI page
		return "WhUserTypeData";
	}
	//7. AJAX Validation for Email
	//8. Excel Export
	
	@GetMapping("/excel")
	public ModelAndView excelExport( @RequestParam(value="id" ,required=false) Integer id) {
		
		ModelAndView mv = new ModelAndView();
		mv.setView(new WhUserTypeExcelView());
		if(id!=null) //if ID come along with Request, exporting one row data
		{
			Optional<WhUserType> opt = service.getOneWhUserType(id);
			mv.addObject("list", Arrays.asList(opt.get()));
		}
		else   //Exporting all rows data
		{
			List<WhUserType> list = service.getAllWhUserTypes();
			mv.addObject("list",list);
		}
	
		return mv;
	}
	//9. PDF Export
	
	@GetMapping("/pdf")
	public ModelAndView pdfExport(@RequestParam(value="id" ,required=false) Integer id) {
		
		ModelAndView m = new ModelAndView();
		
		m.setView(new WhUserTypePdfView());
		
		if(id!=null)  //Export one row data
		{
			Optional<WhUserType> opt = service.getOneWhUserType(id);
			m.addObject("list", Arrays.asList(opt.get()));
		}
		else   //Export all rows Data
		{
			List<WhUserType> list = service.getAllWhUserTypes();
			m.addObject("list",list);
		}
		
		return m;
	}
	//10 Charts
}
