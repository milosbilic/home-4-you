package home.four.you.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import home.four.you.model.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.four.you.repository.EquipmentRepository;
import home.four.you.service.EquipmentService;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository repository;

	@Override
	public Equipment findOne(Long id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public List<Equipment> findAll() {
		return repository.findAll();
	}

	@Override
	public Set<Equipment> findByIds(List<Long> ids) {
		return ids.stream().map(x -> findOne(x)).collect(Collectors.toSet());
	}
	
	

}
