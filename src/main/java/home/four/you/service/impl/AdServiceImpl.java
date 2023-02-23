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
import home.four.you.service.EquipmentService;
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

import static java.util.Optional.ofNullable;

/**
 * Implementation of {@link AdService}.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;
    private final LocationService locationService;
    private final EquipmentService equipmentService;
    private final UserService userService;

    @Override
    @Transactional
    public Ad createAd(CreateAdRequestDto dto) {
        log.debug("Creating ad [{}]", dto);

        var propertyDto = dto.property();
        var location = locationService.findById(propertyDto.locationId())
                .orElseThrow(BadRequestException::new);

        var newAd = new Ad()
                .setTitle(dto.title())
                .setDescription(dto.description())
                .setType(dto.type())
                .setPrice(dto.price())
                .setExpirationDate(calculateExpirationDate())
                .setUser(userService.findOne(1L)); // TODO Real user should be set when security is implemented.

        var property = new Property()
                .setArea(propertyDto.area())
                .setLocation(location)
                .setHeatType(propertyDto.heatType())
                .setNumberOfRooms(propertyDto.numberOfRooms())
                .setBooked(propertyDto.booked())
                .setEquipment(ofNullable(propertyDto.equipmentIds())
                        .map(equipmentService::findByIds)
                        .orElse(null));

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
    public Ad findOne(Long id) {
        log.debug("Finding ad with id {}", id);

        return adRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ad with the ID of " + id + " not found,"));
    }

    @Override
    public void delete(Long id) {
        log.debug("Deleting ad {}", id);

        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> findNewest() {
        log.debug("Finding newest ads...");

        return adRepository.findTop3ByOrderByDateCreatedDesc();
    }

    private Instant calculateExpirationDate() {
        var now = Instant.now();

        return now.plus(90, ChronoUnit.DAYS);
    }

    private void setPropertyType(Property property, CreatePropertyRequestDto propertyDto) {
        if (propertyDto.house() != null) {
            property.setHouse(new House()
                    .setNumberOfFloors(propertyDto.house().numberOfFloors())
                    .setCourtYardArea(propertyDto.house().courtyardArea()));
        } else {
            property.setApartment(new Apartment()
                    .setFloor(propertyDto.apartment().floor()));
        }
    }
}
