package advertising.service.impl;

import java.util.List;
import java.util.Optional;

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

	@Override
	public Optional<Location> findByName(String name) {
		return repository.findByName(name);
	}
	
	@Override
	public List<Location> findByNameStartingWith(String name) {
		return repository.findByNameStartingWith(name);
	}

	@Override
	public Location save(Location location) {
		return repository.save(location);
	}

}
