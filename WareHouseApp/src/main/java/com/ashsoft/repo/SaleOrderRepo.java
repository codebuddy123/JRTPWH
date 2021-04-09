package com.ashsoft.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashsoft.model.SaleOrder;

public interface SaleOrderRepo extends JpaRepository<SaleOrder, Integer> {

}
