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
import org.springframework.web.bind.annotation.ResponseBody;

import com.ashsoft.model.OrderMethod;
import com.ashsoft.service.IOrderMethodService;

@Controller
@RequestMapping("/om")
public class OrderMethodController {
	@Autowired
	private IOrderMethodService service;

	// 1. Display Registration form
	@GetMapping("/register")
	public String displayOrderMethodReg() {
		return "OrderMethodRegister";
	}

	// 2. Saving Data

	@PostMapping("/save")
	public String saveOrderMethod(OrderMethod orderMethod, Model model) {
		// call service
		Integer id = service.saveOrderMethod(orderMethod);

		String msg = " Order Method with ID " + id + " Saved Successfully..";
		model.addAttribute("msg", msg);

		return "OrderMethodRegister";
	}

	// 3. Displaying Data in Table format

	@GetMapping("/all")
	public String displayOrderMethod(Model model) {
		// call service
		List<OrderMethod> list = service.getAllOrderMethods();

		// send to UI
		model.addAttribute("list", list);

		return "OrderMethodData";
	}

	// 4. Deleting Data

	@GetMapping("/delete")
	public String deleteOrderMethod(@RequestParam("id") Integer oid, Model model) {

		if (service.orderMethodIfExist(oid)) {
			// call service
			service.deleteOrderMethod(oid);

			String message = "Order Method with ID '" + oid + "' Deleted Successfully..";

			// send msg to UI
			model.addAttribute("msg", message);
		} else {
			model.addAttribute("msg", oid + " Not Found...!!");
		}
		// Refreshed List
		model.addAttribute("list", service.getAllOrderMethods());

		return "OrderMethodData";
	}

	// 5. Editing Data

	@GetMapping("/edit")
	public String editOrderMethod(@RequestParam("id") Integer id, Model model) {

		// call service
		Optional<OrderMethod> optional = service.getOneOrderMethod(id);

		String page = null;
		if (optional.isPresent()) // if data exist
		{
			// fill object form
			model.addAttribute("orderMethod", optional.get());
			page = "OrderMethodEdit";
		} else {
			page = "redirect:all";
		}

		return page;
	}

	// 6. Updating Data

	@PostMapping("/update")
	public String updateShipmentType(@ModelAttribute OrderMethod orderMethod, Model model) {

		// call service layer
		service.updateOrderMethod(orderMethod);

		// Sending message to UI
		model.addAttribute("msg", "Order Method with ID '" + orderMethod.getId() + "' Updated Successfully..");

		// Sending Fresh data to UI

		model.addAttribute("list", service.getAllOrderMethods());

		// going back to UI page
		return "OrderMethodData";
	}
	
    //7. AJAX Validation
	
	@GetMapping("/validateCode")
	public @ResponseBody String validateOrderCode(@RequestParam("code") String orderCode) {
		String message = "";
		if (service.isOrderMethodExistByCode(orderCode)) {
			message = "Order Method '" + orderCode + "' Exist! Try different!";
		}

		return message;
	}

}
