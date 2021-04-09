package com.ashsoft.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.PurchaseDtl;
import com.ashsoft.model.PurchaseOrder;
import com.ashsoft.repo.PurchaseDtlRepo;
import com.ashsoft.repo.PurchaseOrderRepo;

@Service
public class PurchaseOrderServiceImpl implements IPurchaseOrderService {

	@Autowired
	private PurchaseOrderRepo repo;

	@Autowired
	private PurchaseDtlRepo dtlRepo;

	@Override
	public Integer savePurchaseOrder(PurchaseOrder purchaseOrder) {

		return repo.save(purchaseOrder).getId();
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrder() {

		return repo.findAll();
	}

	@Override
	public PurchaseOrder getOnePurchaseOrder(Integer id) {
		return repo.findById(id).get();
	}

	// ------SCREEN 2 METHODS------

	@Override
	public void addPoPart(PurchaseDtl purchaseDtl) {

		dtlRepo.save(purchaseDtl);
	}

	@Override
	public List<PurchaseDtl> getPurchaseDtlByOrderId(Integer orderId) {

		return dtlRepo.getPurchaseDtlByOrderId(orderId);
	}

	@Override
	public void deletePurchaseDtlByOrderId(Integer orderId) {

		dtlRepo.deleteById(orderId);

	}

	@Override
	public Integer getCountOfItemsByOrderId(Integer orderId) {

		return dtlRepo.getCountOfItemsByOrderId(orderId);
	}

	@Override
	public String getStatusByOrderId(Integer orderId) {

		return repo.getStatusByOrderId(orderId);

	}

	@Override
	@Transactional
	public void updatePoStatusByOrderId(String orderStatus, Integer orderId) {

		repo.updatePoStatusByOrderId(orderStatus, orderId);
	}

	@Override
	public Optional<Integer> getPurchaseDtlByPartIdAndPoId(Integer partId, Integer orderId) {

		return dtlRepo.getPurchaseDtlByPartIdAndPoId(partId, orderId);
	}

	@Transactional
	public void updatePurchaseDtlQtyByDtlId(Integer dtlId, Integer newQty) {
		dtlRepo.updatePurchaseDtlQtyByDtlId(dtlId, newQty);
	}
}
