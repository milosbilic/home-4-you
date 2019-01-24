package advertising.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import advertising.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

	Optional<Location> findByName(String name);
	
	List<Location> findByNameStartingWith(String name);

}
