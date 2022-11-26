package home.four.you.repository;

import java.util.List;
import java.util.Optional;

import home.four.you.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

	Optional<Location> findByName(String name);
	
	List<Location> findByNameStartingWith(String name);
}
