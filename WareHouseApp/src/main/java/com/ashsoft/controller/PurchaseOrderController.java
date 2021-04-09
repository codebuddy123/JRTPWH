package com.ashsoft.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashsoft.model.PurchaseOrder;
import com.ashsoft.consts.POStatus;
import com.ashsoft.model.PurchaseDtl;
import com.ashsoft.service.IPartService;
import com.ashsoft.service.IPurchaseOrderService;
import com.ashsoft.service.IShipmentTypeService;
import com.ashsoft.service.IWhUserTypeService;

@Controller
@RequestMapping("/po")
public class PurchaseOrderController {

	@Autowired
	private IPurchaseOrderService service;

	@Autowired
	private IShipmentTypeService shipmentTypeService;

	@Autowired
	private IWhUserTypeService whUserTypeService;

	@Autowired
	private IPartService partService;

	// Method for Drop-down Integrations

	private void commonChildUi(Model model) {

		Map<Integer, String> shipmentMap = shipmentTypeService.getEnableShipments();
		model.addAttribute("shipments", shipmentMap);

		Map<Integer, String> whUserMap = whUserTypeService.getWhUserIdAndCodeByType("Vendor");
		model.addAttribute("whusers", whUserMap);

	}

	// 1.Show Reg
	@GetMapping("/register")
	public String showRegForm(Model model) {

		commonChildUi(model);
		return "PurchaseOrderRegister";

	}
	// 2. Save Data

	@PostMapping("/save")
	public String savePurchaseOrder(@ModelAttribute PurchaseOrder purchaseOrder, Model model) {

		// call service

		Integer id = service.savePurchaseOrder(purchaseOrder);

		String message = "Purchase Order with " + id + " Saved Successfully..";
		model.addAttribute("msg", message);
		commonChildUi(model);
		return "PurchaseOrderRegister";
	}
	// 3. Display Data

	@GetMapping("/all")
	public String displayPurchaseOrder(Model model) {

		// call service
		List<PurchaseOrder> list = service.getAllPurchaseOrder();

		// send to UI
		model.addAttribute("list", list);

		return "PurchaseOrderData";
	}

	// ------------------SCREEN 2 METHODS ---------------------

	@GetMapping("/parts")
	public String showPurchasePartsPage(@RequestParam("poId") Integer orderId, Model model) {
		// Load PO object by ID
		PurchaseOrder po = service.getOnePurchaseOrder(orderId);

		// Sending PO object to UI for ORDERID and ORDERCODE
		model.addAttribute("po", po);

		// Create parts drop-down
		commonUiForParts(model);

		// Sending form backing object

		PurchaseDtl purchaseDtl = new PurchaseDtl();
		purchaseDtl.setPo(po);
		model.addAttribute("purchaseDtl", purchaseDtl);

		// Display added Dtls as HTML TABLE
		List<PurchaseDtl> poDtls = service.getPurchaseDtlByOrderId(po.getId());
		model.addAttribute("poDtls", poDtls);

		return "PurchaseParts";
	}

	private void commonUiForParts(Model model) {
		model.addAttribute("parts", partService.getPartIdAndCode());
	}

	// Method for Saving PurchaseDtl

	@PostMapping("/addPart")
	public String addPoPart(@ModelAttribute PurchaseDtl purchaseDtl) {

		// System.out.println(purchaseDtl);

		Integer orderId = purchaseDtl.getPo().getId();
		Integer partId = purchaseDtl.getPart().getId();

		Optional<Integer> opt = service.getPurchaseDtlByPartIdAndPoId(partId, orderId);

		if (opt.isPresent()) // if part is already present
		{
			service.updatePurchaseDtlQtyByDtlId(opt.get(), purchaseDtl.getQty());
		} else {
			service.addPoPart(purchaseDtl); // if not present,add part
		}

		// if(count>0 and current status is not picking ) then update status=picking

		if (service.getCountOfItemsByOrderId(orderId) > 0
				&& !POStatus.PICKING.getValue().equals(service.getStatusByOrderId(orderId))) {
			service.updatePoStatusByOrderId(POStatus.PICKING.getValue(), orderId);
		}

		return "redirect:parts?poId=" + orderId; // redirect to same page with orderId
	}

	// Method for Deleting one row( PurchaseDtl)
	/*
	 * This method takes two inputs, poId(OrderId) and dtlId. dtlId is used for
	 * deleting one row poId is used for redirecting to same Page
	 */
	@GetMapping("/removeDtl")
	public String removePurchaseDtl(@RequestParam("poId") Integer orderId, @RequestParam("dtlId") Integer dtlId) {
		service.deletePurchaseDtlByOrderId(dtlId);

		// if (count=0 and current status is not open) then update status = open

		if (service.getCountOfItemsByOrderId(orderId) == 0
				&& !POStatus.OPEN.getValue().equals(service.getStatusByOrderId(orderId))) {
			service.updatePoStatusByOrderId(POStatus.OPEN.getValue(), orderId);
		}

		return "redirect:parts?poId=" + orderId;
	}

	@GetMapping("/placeOrder")
	public String placeOrderById(@RequestParam("poId") Integer orderId) {

		service.updatePoStatusByOrderId(POStatus.ORDERED.getValue(), orderId);
		return "redirect:parts?poId=" + orderId;
	}
	
	//Quantity increase and decrease buttons methods
	@GetMapping("/increaseByOne")
	public String increaseByOne(
			@RequestParam("poId")Integer orderId,
			@RequestParam("dtlId")Integer dtlId
			) 
	{
		service.updatePurchaseDtlQtyByDtlId(dtlId, 1);
		return "redirect:parts?poId="+orderId;
	}
	@GetMapping("/reduceByOne")
	public String reduceByOne(
			@RequestParam("poId")Integer orderId,
			@RequestParam("dtlId")Integer dtlId
			) 
	{
		service.updatePurchaseDtlQtyByDtlId(dtlId, -1);
		return "redirect:parts?poId="+orderId;
	}
}
