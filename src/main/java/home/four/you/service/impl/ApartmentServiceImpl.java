package home.four.you.service.impl;

import home.four.you.exception.NotFoundException;
import home.four.you.model.entity.Apartment;
import home.four.you.repository.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.four.you.service.ApartmentService;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {
	
	@Autowired
	private ApartmentRepository repo;

	@Override
	public Apartment findOne(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new NotFoundException("Apartment with id of " + id + " not found!"));
	}

	@Override
	public Apartment save(Apartment apartment) {
		return repo.save(apartment);
	}

}
