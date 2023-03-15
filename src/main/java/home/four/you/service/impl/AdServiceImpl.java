package home.four.you.service.impl;

import home.four.you.exception.BadRequestException;
import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreatePropertyRequestDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Apartment;
import home.four.you.model.entity.House;
import home.four.you.model.entity.Property;
import home.four.you.repository.AdRepository;
import home.four.you.service.AdService;
import home.four.you.service.LocationService;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Implementation of {@link AdService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final LocationService locationService;
    private final UserService userService;

    @Override
    @Transactional
    public Ad createAd(CreateAdRequestDto dto) {
        log.debug("Creating ad [{}]", dto);

        var propertyDto = dto.property();
        var location = locationService.findById(propertyDto.locationId())
                .orElseThrow(() -> new BadRequestException("Location not found."));

        var newAd = new Ad()
                .setTitle(dto.title())
                .setDescription(dto.description())
                .setType(dto.type())
                .setPrice(dto.price())
                .setExpirationDate(calculateExpirationDate())
                .setOwner(userService.findById(1L)); // TODO Real user should be set when security is implemented.

        var property = new Property()
                .setArea(propertyDto.area())
                .setLocation(location)
                .setHeatType(propertyDto.heatType())
                .setNumberOfRooms(propertyDto.numberOfRooms())
                .setBooked(propertyDto.booked())
                .setEquipment(propertyDto.equipment());


        setPropertyType(property, propertyDto);
        newAd.setProperty(property);

        return adRepository.save(newAd);
    }

    @Override
    public Page<Ad> findAll(Pageable pageable) {
        log.debug("Finding all ads...");

        return adRepository.findAll(pageable);
    }

    @Override
    public Ad findById(Long id) {
        log.debug("Finding ad with id {}", id);

        return adRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting ad {}", id);

        adRepository.findById(id)
                .ifPresentOrElse(adRepository::delete, () -> {
                    throw new ResourceNotFoundException();
                });
    }

    @Override
    public List<Ad> findNewest() {
        log.debug("Finding newest ads...");

        return adRepository.findTop3ByOrderByCreatedAtDesc();
    }

    private Instant calculateExpirationDate() {
        var now = Instant.now();

        return now.plus(90, ChronoUnit.DAYS);
    }

    private void setPropertyType(Property property, CreatePropertyRequestDto propertyDto) {
        if (propertyDto.house() != null) {
            property.setHouse(new House()
                    .setNumberOfFloors(propertyDto.house().numberOfFloors())
                    .setCourtyardArea(propertyDto.house().courtyardArea()));
        } else {
            property.setApartment(new Apartment()
                    .setFloor(propertyDto.apartment().floor()));
        }
    }
}
