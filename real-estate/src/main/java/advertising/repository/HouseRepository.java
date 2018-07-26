package advertising.repository;

import javax.transaction.Transactional;

import advertising.model.House;

@Transactional
public interface HouseRepository extends RealEstateRepository<House> {
	
}
