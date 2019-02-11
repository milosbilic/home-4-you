package advertising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advertising.exception.NotFoundException;
import advertising.model.Apartment;
import advertising.repository.ApartmentRepository;
import advertising.service.ApartmentService;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
	
	@Autowired
	private ApartmentRepository repo;

	@Override
	public Apartment findOne(Long id) {
		Apartment apartment = repo.findOne(id);
		if (apartment == null)
			throw new NotFoundException("Apartment with id of " + id + " not found!");
		return apartment;
	}

	@Override
	public Apartment save(Apartment apartment) {
		return repo.save(apartment);
	}

}
