package advertising.service;

import java.util.List;

import advertising.model.Location;

public interface LocationService {

	Location findOne(Long id);
	
	Location findByName(String name);

	List<Location> findByNameStartingWith(String name);
	
}
