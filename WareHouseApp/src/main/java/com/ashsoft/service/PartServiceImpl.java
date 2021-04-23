package com.ashsoft.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashsoft.model.Part;
import com.ashsoft.repo.PartRepo;
import com.ashsoft.util.AppUtil;

@Service
public class PartServiceImpl implements IPartService {

	@Autowired
	private PartRepo repo;

	@Override
	public Integer savePart(Part part) {

		return repo.save(part).getId();
	}

	@Override
	public List<Part> getAllParts() {

		return repo.findAll();
	}

	@Override
	public void deletePart(Integer id) {

		repo.deleteById(id);

	}

	@Override
	public boolean partIfExist(Integer id) {

		return repo.existsById(id);
	}

	@Override
	public Optional<Part> getOnePart(Integer id) {

		return repo.findById(id);
	}

	@Override
	public void updatePart(Part part) {

		repo.save(part);
	}

	@Override
	public Map<Integer, String> getPartIdAndCode() {

		
		List<Object[]> list = repo.getPartIdAndCode();

		Map<Integer, String> map = AppUtil.convertToMap(list);
		
		return map;
	}
}
