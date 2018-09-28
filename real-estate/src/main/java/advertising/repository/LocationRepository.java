package advertising.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import advertising.model.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
