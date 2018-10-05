package advertising.repository;

import javax.transaction.Transactional;

import advertising.model.Apartment;

@Transactional
public interface ApartmentRepository extends RealEstateRepository<Apartment> {

}
