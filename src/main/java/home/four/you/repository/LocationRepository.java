package home.four.you.repository;

import home.four.you.model.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for {@link Location} entity.
 */
public interface LocationRepository extends JpaRepository<Location, Long> {

    /**
     * Finds location by provided name.
     *
     * @param name Location name.
     * @return Matching location or empty {@link Optional}.
     */
    Optional<Location> findByName(String name);
}
