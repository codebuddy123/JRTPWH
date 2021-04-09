package com.ashsoft.service;

import java.util.List;
import java.util.Optional;

import com.ashsoft.model.PurchaseDtl;
import com.ashsoft.model.PurchaseOrder;

public interface IPurchaseOrderService {

	public Integer savePurchaseOrder(PurchaseOrder purchaseOrder);

	public List<PurchaseOrder> getAllPurchaseOrder();

	public PurchaseOrder getOnePurchaseOrder(Integer id);

	// SCREEN 2 METHODS
	public void addPoPart(PurchaseDtl purchaseDtl);

	public List<PurchaseDtl> getPurchaseDtlByOrderId(Integer orderId);

	public void deletePurchaseDtlByOrderId(Integer orderId);

	public void updatePoStatusByOrderId(String orderStatus, Integer orderId);

	public String getStatusByOrderId(Integer orderId);

	public Integer getCountOfItemsByOrderId(Integer orderId);
	
	public Optional<Integer> getPurchaseDtlByPartIdAndPoId(Integer partId, Integer orderId);
	
	public void updatePurchaseDtlQtyByDtlId(Integer dtlId, Integer newQty);
}
