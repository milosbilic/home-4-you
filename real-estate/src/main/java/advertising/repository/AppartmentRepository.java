package advertising.repository;

import javax.transaction.Transactional;

import advertising.model.Appartment;

@Transactional
public interface AppartmentRepository extends RealEstateRepository<Appartment> {

}
