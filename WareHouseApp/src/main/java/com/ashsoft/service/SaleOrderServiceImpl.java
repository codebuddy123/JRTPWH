package com.ashsoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.SaleOrder;
import com.ashsoft.repo.SaleOrderRepo;

@Service
public class SaleOrderServiceImpl implements ISaleOrderService {
	
	@Autowired
	private SaleOrderRepo repo;
	@Override
	public Integer saveSaleOrder(SaleOrder saleOrder) {
		
		return repo.save(saleOrder).getId();
	}

	@Override
	public List<SaleOrder> getAllSaleOrder() {
		
		return repo.findAll();
	}

}
