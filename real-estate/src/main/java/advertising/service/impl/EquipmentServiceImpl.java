package advertising.service.impl;

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

}
