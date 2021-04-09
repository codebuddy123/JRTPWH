package com.ashsoft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ashsoft.model.Part;

public interface IPartService {

	public Integer savePart(Part part);
	public List<Part> getAllParts();
	public void deletePart(Integer id);
	public boolean partIfExist(Integer id);
	public Optional<Part> getOnePart(Integer id);
	public void updatePart(Part part);
	
	//SCREEN 2 Methods
	public Map<Integer, String> getPartIdAndCode();
}
