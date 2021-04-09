package com.ashsoft.service;

import java.util.List;
import com.ashsoft.model.SaleOrder;

public interface ISaleOrderService {
	
	public Integer saveSaleOrder(SaleOrder saleOrder);
	public List<SaleOrder> getAllSaleOrder();
	
}
