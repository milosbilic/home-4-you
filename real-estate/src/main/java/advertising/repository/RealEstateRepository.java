package advertising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import advertising.model.RealEstate;

@NoRepositoryBean
public interface RealEstateRepository<T extends RealEstate> extends JpaRepository<T, Long> {

}
