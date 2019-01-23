package advertising.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advertising.model.Equipment;
import advertising.repository.EquipmentRepository;
import advertising.service.EquipmentService;

@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {

	@Autowired
	private EquipmentRepository repository;

	@Override
	public Equipment findOne(Long id) {
		return repository.findOne(id);
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
