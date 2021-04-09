package com.ashsoft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashsoft.model.ShipmentType;

public interface ShipmentTypeRepo extends JpaRepository<ShipmentType, Integer> {
	
	@Query("SELECT id,shipmentCode FROM ShipmentType WHERE enableShipment = 'Yes'")
	public List<Object[]> getEnableShipments();
}
