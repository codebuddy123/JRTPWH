package com.ashsoft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashsoft.model.Uom;

public interface UomRepo extends JpaRepository<Uom, Integer> {

	@Query("SELECT id,uomModel FROM Uom")
	public List<Object[]> getUomIdandModel();
}
