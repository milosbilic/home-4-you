package advertising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		return repo.findOne(id);
	}

	@Override
	public Apartment save(Apartment apartment) {
		return repo.save(apartment);
	}

}
