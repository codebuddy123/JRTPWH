package com.ashsoft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.ashsoft.model.ShipmentType;

public interface IShipmentTypeService {
	
	public Integer saveShipmentType(ShipmentType shipmentType);
	public List<ShipmentType> getAllShipmentTypes();
	public void deleteShipmentType(Integer id);
	public boolean shipmentIfExist(Integer sid);
	public Optional<ShipmentType> getOneShipmentType(Integer id);
	public void updateShipmentType(ShipmentType shipmentType);
	public Page<ShipmentType> getAllShipmentsByPage(Pageable p);
	public Map<Integer,String> getEnableShipments();
}
