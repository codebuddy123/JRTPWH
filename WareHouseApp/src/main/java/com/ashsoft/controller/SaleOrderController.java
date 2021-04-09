package com.ashsoft.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ashsoft.model.PurchaseOrder;
import com.ashsoft.model.SaleOrder;
import com.ashsoft.service.ISaleOrderService;
import com.ashsoft.service.IShipmentTypeService;
import com.ashsoft.service.IWhUserTypeService;

@Controller
@RequestMapping("/so")
public class SaleOrderController {

	@Autowired
	private ISaleOrderService service;

	@Autowired
	private IShipmentTypeService shipmentTypeService;

	@Autowired
	private IWhUserTypeService whUserTypeService;
	
	
	// 1. Drop-down Integrations
	private void commonChildUi(Model model) {
		model.addAttribute("shipments", shipmentTypeService.getEnableShipments());
		model.addAttribute("whusers", whUserTypeService.getWhUserIdAndCodeByType("Customer"));
	}

	// 2. Show regform
	@GetMapping("/register")
	public String getRegForm(Model model) {

		commonChildUi(model);
		return "SaleOrderRegister";
	}

	// 3. Save Data

	@PostMapping("/save")
	public String saveSaleOrder(@ModelAttribute SaleOrder saleOrder, Model model) {

		// call service
		Integer id = service.saveSaleOrder(saleOrder);

		String message = "Sale Order with ID: " + id + " Saved Successfully...";

		model.addAttribute("msg", message);

		commonChildUi(model);

		return "SaleOrderRegister";
	}

	// 4. Display Data

	@GetMapping("/all")
	public String displaySaleOrder(Model model) {

		// call service
		List<SaleOrder> list = service.getAllSaleOrder();

		// send to UI
		model.addAttribute("list", list);

		return "SaleOrderData";
	}
}
