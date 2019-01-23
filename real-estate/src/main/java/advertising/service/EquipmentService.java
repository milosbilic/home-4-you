package advertising.service;

import java.util.List;
import java.util.Set;

import advertising.model.Equipment;

public interface EquipmentService {

	Equipment findOne(Long id);
	
	List<Equipment> findAll();
	
	Set<Equipment> findByIds(List<Long> ids);
	
}
