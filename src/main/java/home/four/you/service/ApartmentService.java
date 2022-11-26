package home.four.you.service;

import home.four.you.model.entity.Apartment;

public interface ApartmentService {

	Apartment findOne(Long id);
	
	Apartment save(Apartment apartment);
	
}
