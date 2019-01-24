package advertising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advertising.model.House;
import advertising.repository.HouseRepository;
import advertising.service.HouseService;

@Service
@Transactional
public class HouseServiceImpl implements HouseService {

	@Autowired
	private HouseRepository repo;
	
	@Override
	public House findOne(Long id) {
		return repo.findOne(id);
	}

	@Override
	public House save(House house) {
		return repo.save(house);
	}

}
