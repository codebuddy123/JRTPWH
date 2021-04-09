package com.ashsoft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ashsoft.model.OrderMethod;

public interface IOrderMethodService  {
	
	public Integer saveOrderMethod(OrderMethod orderMethod);
	public List<OrderMethod> getAllOrderMethods();
	public void deleteOrderMethod(Integer id);
	public boolean orderMethodIfExist(Integer id);
	public Optional<OrderMethod> getOneOrderMethod(Integer id);
	public void updateOrderMethod(OrderMethod orderMethod);
	public boolean isOrderMethodExistByCode(String orderCode);
	public Map<Integer, String> getOrderMethodIdAndCode(String mode);
}
