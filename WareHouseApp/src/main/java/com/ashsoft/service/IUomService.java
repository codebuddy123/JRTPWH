package com.ashsoft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ashsoft.model.Uom;

public interface IUomService {
	
	public Integer saveUom(Uom uom);
	public List<Uom> getAllUoms();
	public void deleteUom(Integer id);
	public boolean uomIfExist(Integer id);
	public Optional<Uom> getOneUom(Integer id);
	public void updateUom(Uom uom);
	public Map<Integer, String> getUomIdAndModel();
}
