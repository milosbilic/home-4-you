package advertising.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advertising.model.Location;
import advertising.repository.LocationRepository;
import advertising.service.LocationService;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepository repository;
	
	@Override
	public Location findOne(Long id) {
		return repository.findOne(id);
	}

}
