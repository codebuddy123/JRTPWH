package com.ashsoft.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.Uom;
import com.ashsoft.repo.UomRepo;

@Service
public class UomServiceImpl implements IUomService {
	
	@Autowired
	private UomRepo repo;

	@Override
	public Integer saveUom(Uom uom) {
		
		return repo.save(uom).getId();
	}

	@Override
	public List<Uom> getAllUoms() {
		
		return repo.findAll();
	}

	@Override
	public void deleteUom(Integer id) {
		
		repo.deleteById(id);
	}

	@Override
	public boolean uomIfExist(Integer id) {
		
		return repo.existsById(id);
	}

	@Override
	public Optional<Uom> getOneUom(Integer id) {
		
		return repo.findById(id);
	}

	@Override
	public void updateUom(Uom uom) {
		
		repo.save(uom);
	}
	
	@Override
	public Map<Integer, String> getUomIdAndModel() {
		
		List<Object[]> list = repo.getUomIdandModel();
		
		Map<Integer, String> map = new LinkedHashMap<>();
		for(Object[] ob:list)
		{
			map.put(Integer.parseInt(ob[0].toString()), (String) ob[1]);
		}
		return map;
	}
}
