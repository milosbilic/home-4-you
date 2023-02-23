package home.four.you.service;

import home.four.you.model.entity.Location;

import java.util.List;
import java.util.Optional;

/**
 * Service for {@link Location} entity related operations.
 */
public interface LocationService {

    Location save(Location location);

    Location findOne(Long id);

    Optional<Location> findByName(String name);

    List<Location> findByNameStartingWith(String name);

    /**
     * Finds a location by provided ID.
     *
     * @param id Location ID.
     * @return Matching location, or empty {@link Optional}.
     */
    Optional<Location> findById(Long id);
}
