package com.ashsoft.service;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.WhUserType;
import com.ashsoft.repo.WhUserTypeRepo;

@Service
public class WhUserTypeServiceImpl implements IWhUserTypeService {

	@Autowired
	private WhUserTypeRepo repo;
	@Override
	public Integer saveWhUserType(WhUserType whUserType) {
		
		return repo.save(whUserType).getId() ;
	}

	@Override
	public List<WhUserType> getAllWhUserTypes() {
		
		return repo.findAll();
	}

	@Override
	public void deleteWhUserType(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public boolean whUserTypeIfExist(Integer id) {
		
		return repo.existsById(id);
	}

	@Override
	public Optional<WhUserType> getOneWhUserType(Integer id) {
		
		return repo.findById(id);
	}

	@Override
	public void updateWhUserType(WhUserType whUserType) {
		
		repo.save(whUserType);
	}
    
    @Override
    public Map<Integer, String> getWhUserIdAndCodeByType(String type) {
    	
    	Map<Integer, String> map = new HashedMap<>();
    	
    	List<Object[]> list = repo.getWhUserIdAndCodeByType(type);
    	
    	for(Object[] ob:list)
    	{
    		map.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
    	}
    	return map;
    }
}
