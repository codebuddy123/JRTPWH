package com.ashsoft.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ashsoft.model.WhUserType;

public interface WhUserTypeRepo extends JpaRepository<WhUserType, Integer> {
	
	
	@Query("SELECT id,userCode FROM WhUserType WHERE userType= :type")
	public List<Object[]> getWhUserIdAndCodeByType(String type);
}
