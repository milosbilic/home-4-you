package advertising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advertising.model.House;
import advertising.model.RealEstate;
import advertising.repository.HouseRepository;
import advertising.service.RealEstateService;

@Service
@Transactional
public class HouseServiceImpl implements RealEstateService {

	@Autowired
	private HouseRepository repo;
	
	@Override
	public House findOne(Long id) {
		return (House) repo.findOne(id);
	}

	@Override
	public House save(RealEstate realEstate) {
		House house = (House) realEstate;
		return (House) repo.save(house);
	}

}
