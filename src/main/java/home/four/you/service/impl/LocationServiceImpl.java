package home.four.you.service.impl;

import home.four.you.model.entity.Location;
import home.four.you.repository.LocationRepository;
import home.four.you.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link LocationService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Override
    public Location findOne(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Optional<Location> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Location> findByNameStartingWith(String name) {
        return null;
    }

    @Override
    public Optional<Location> findById(Long id) {
        log.debug("Finding location {}", id);

        return repository.findById(id);
    }

    @Override
    public Location save(Location location) {
        return repository.save(location);
    }

}
