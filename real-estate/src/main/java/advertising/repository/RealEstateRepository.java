package advertising.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import advertising.model.RealEstate;

@NoRepositoryBean
public interface RealEstateRepository<T extends RealEstate> extends JpaRepository<T, Long> {

	@Query("select re from RealEstate re where type(re) = 'House'")
	List<RealEstate> getHouses();
	
}
