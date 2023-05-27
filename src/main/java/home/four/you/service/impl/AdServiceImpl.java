package home.four.you.service.impl;

import home.four.you.exception.BadRequestException;
import home.four.you.model.dto.AdSearchFilter;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreatePropertyRequestDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Apartment;
import home.four.you.model.entity.House;
import home.four.you.model.entity.Property;
import home.four.you.repository.AdRepository;
import home.four.you.security.UserPrincipal;
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
import java.util.Optional;

import static home.four.you.exception.ErrorMessage.LOCATION_NOT_FOUND;
import static home.four.you.repository.AdRepository.Specs.byAreaGreaterThanOrEqual;
import static home.four.you.repository.AdRepository.Specs.byAreaLessThanOrEqual;
import static home.four.you.repository.AdRepository.Specs.byNumberOfRoomsGreaterThanOrEqual;
import static home.four.you.repository.AdRepository.Specs.byNumberOfRoomsLessThanOrEqual;
import static home.four.you.repository.AdRepository.Specs.byPriceGreaterThanOrEqual;
import static home.four.you.repository.AdRepository.Specs.byPriceLessThanOrEqual;
import static home.four.you.repository.AdRepository.Specs.byPropertyType;
import static home.four.you.repository.AdRepository.Specs.byType;

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
    public Ad createAd(CreateAdRequestDto dto, UserPrincipal caller) {
        log.debug("Creating ad [{}]", dto);

        var propertyDto = dto.property();
        var location = locationService.findById(propertyDto.locationId())
            .orElseThrow(() -> new BadRequestException(LOCATION_NOT_FOUND));

        var newAd = new Ad()
            .setTitle(dto.title())
            .setDescription(dto.description())
            .setType(dto.type())
            .setPrice(dto.price())
            .setExpirationDate(calculateExpirationDate())
            .setOwner(userService.getById(caller.getId()));

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
    public Page<Ad> search(AdSearchFilter filter, Pageable pageable) {
        log.debug("Searching ads by filter [{}], page {}", filter, pageable.getPageNumber());

        return adRepository.findAll(byType(filter.type())
                .and(byPriceGreaterThanOrEqual(filter.minPrice()))
                .and(byPriceLessThanOrEqual(filter.maxPrice()))
                .and(byPropertyType(filter.propertyType()))
                .and(byAreaGreaterThanOrEqual(filter.minArea()))
                .and(byAreaLessThanOrEqual(filter.maxArea()))
                .and(byNumberOfRoomsGreaterThanOrEqual(filter.minNumberOfRooms()))
                .and(byNumberOfRoomsLessThanOrEqual(filter.maxNumberOfRooms())),
            pageable);
    }

    @Override
    public Optional<Ad> findById(Long id) {
        log.debug("Finding ad with id {}", id);

        return adRepository.findById(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Deleting ad {}", id);

        adRepository.deleteById(id);
    }

    @Override
    public List<Ad> findLatest() {
        log.debug("Finding latest ads...");

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
