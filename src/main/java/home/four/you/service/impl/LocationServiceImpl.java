package home.four.you.service.impl;

import home.four.you.model.entity.Location;
import home.four.you.repository.LocationRepository;
import home.four.you.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository repository;

    @Override
    public Location findOne(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Optional<Location> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public List<Location> findByNameStartingWith(String name) {
        return repository.findByNameStartingWith(name);
    }

    @Override
    public Location save(Location location) {
        return repository.save(location);
    }

}
