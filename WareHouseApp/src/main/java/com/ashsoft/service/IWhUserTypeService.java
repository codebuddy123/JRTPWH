package com.ashsoft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ashsoft.model.WhUserType;

public interface IWhUserTypeService {
	
	public Integer saveWhUserType(WhUserType whUserType);
	public List<WhUserType> getAllWhUserTypes();
	public void deleteWhUserType(Integer id);
	public boolean whUserTypeIfExist(Integer id);
	public Optional<WhUserType> getOneWhUserType(Integer id);
	public void updateWhUserType(WhUserType whUserType);
	public Map<Integer, String> getWhUserIdAndCodeByType(String type);
}
