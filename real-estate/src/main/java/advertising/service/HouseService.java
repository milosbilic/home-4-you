package advertising.service;

import java.util.Optional;

import advertising.model.House;

public interface HouseService {

	House findOne(Long id);

	House save(House house);

}
