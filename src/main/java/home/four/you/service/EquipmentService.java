package home.four.you.service;

import java.util.List;
import java.util.Set;

import home.four.you.model.entity.Equipment;

public interface EquipmentService {

	Equipment findOne(Long id);
	
	List<Equipment> findAll();
	
	Set<Equipment> findByIds(List<Long> ids);
	
}
