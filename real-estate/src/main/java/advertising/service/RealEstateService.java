package advertising.service;

import advertising.model.RealEstate;

public interface RealEstateService {

	RealEstate findOne(Long id);
	
	RealEstate save(RealEstate realEstate);
	
}
