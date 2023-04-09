package home.four.you.service.impl;

import home.four.you.model.entity.Location;
import home.four.you.repository.LocationRepository;
import home.four.you.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    private final LocationRepository repository;

    @Override
    public Optional<Location> findById(Long id) {
        log.info("Finding location {}", id);

        return repository.findById(id);
    }
}
