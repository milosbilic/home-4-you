package advertising.service;

import advertising.model.Apartment;

public interface ApartmentService {

	Apartment findOne(Long id);
	
	Apartment save(Apartment apartment);
	
}
