package com.ashsoft.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ashsoft.model.ShipmentType;
import com.ashsoft.repo.ShipmentTypeRepo;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepo repo; // HAS A

	@Override
	public Integer saveShipmentType(ShipmentType shipmentType) {

		shipmentType = repo.save(shipmentType);
		Integer id = shipmentType.getId();
		return id;
	}

	@Override
	public List<ShipmentType> getAllShipmentTypes() {
		List<ShipmentType> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteShipmentType(Integer id) {

		repo.deleteById(id);
	}

	@Override
	public boolean shipmentIfExist(Integer sid) {

		boolean b = repo.existsById(sid);
		return b;
	}

	@Override
	public Optional<ShipmentType> getOneShipmentType(Integer id) {
		Optional<ShipmentType> opt = repo.findById(id);
		return opt;
	}

	@Override
	public void updateShipmentType(ShipmentType st) {
		repo.save(st);

	}

	@Override
	public Page<ShipmentType> getAllShipmentsByPage(Pageable p) {

		return repo.findAll(p);
	}

	@Override
	public Map<Integer, String> getEnableShipments() {

		Map<Integer, String> map = new HashMap<>();
		List<Object[]> list = repo.getEnableShipments();

		for (Object[] ob : list) {
			map.put(Integer.parseInt(ob[0].toString()), ob[1].toString());
		}
		return map;
	}
}
