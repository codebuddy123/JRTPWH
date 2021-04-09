package com.ashsoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ashsoft.model.PurchaseOrder;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Integer>{
	
	@Modifying
	@Query("UPDATE PurchaseOrder SET defStatus=:orderStatus WHERE id=:orderId")
	void updatePoStatusByOrderId(String orderStatus, Integer orderId);
		
	@Query("SELECT defStatus FROM PurchaseOrder WHERE id=:orderId")
	String getStatusByOrderId(Integer orderId);
}
