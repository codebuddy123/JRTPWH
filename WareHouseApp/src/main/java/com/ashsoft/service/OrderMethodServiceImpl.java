package com.ashsoft.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.OrderMethod;
import com.ashsoft.repo.OrderMethodRepo;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepo repo;

	@Override
	public Integer saveOrderMethod(OrderMethod orderMethod) {

		OrderMethod om = repo.save(orderMethod);
		Integer id = om.getId();
		return id;
	}

	@Override
	public List<OrderMethod> getAllOrderMethods() {

		List<OrderMethod> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteOrderMethod(Integer id) {

		repo.deleteById(id);

	}

	@Override
	public boolean orderMethodIfExist(Integer id) {
		boolean b = repo.existsById(id);
		return b;
	}

	@Override
	public Optional<OrderMethod> getOneOrderMethod(Integer id) {

		Optional<OrderMethod> optional = repo.findById(id);
		return optional;
	}

	@Override
	public void updateOrderMethod(OrderMethod orderMethod) {

		repo.save(orderMethod);
	}
   
	@Override
	public boolean isOrderMethodExistByCode(String orderCode) {
		/*Integer count = repo.getOrderMethodCountByCode(orderCode);
		boolean exist = count>0? true:false;
		return exist;*/
		return  repo.getOrderMethodCountByCode(orderCode) > 0;
	}
	
	@Override
	public Map<Integer, String> getOrderMethodIdAndCode(String mode) {
		
		List<Object[]> list = repo.getOrderMethodIdAndCode(mode);
		
		Map<Integer, String> map = new LinkedHashMap<>();
		
		for(Object[] ob : list)
		{
			map.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
		}
		
		return map;
	}
}
