package home.four.you.service.impl;

import home.four.you.exception.BadRequestException;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateHouseDto;
import home.four.you.model.dto.CreatePropertyRequestDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Equipment;
import home.four.you.model.entity.Location;
import home.four.you.model.entity.User;
import home.four.you.repository.AdRepository;
import home.four.you.service.AdService;
import home.four.you.service.EquipmentService;
import home.four.you.service.LocationService;
import home.four.you.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static home.four.you.TestUtil.generateId;
import static home.four.you.model.entity.Equipment.INTERNET;
import static home.four.you.model.entity.Equipment.TV;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AdServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class AdServiceImplTest {

    AdService service;

    @Mock
    AdRepository adRepository;

    @Mock
    LocationService locationService;

    @Mock
    EquipmentService equipmentService;

    @Mock
    UserService userService;

    @BeforeEach
    void setUp() {
        service = new AdServiceImpl(adRepository, locationService, userService);
    }


    @Test
    @DisplayName("Create ad - location not found")
    void createAd_locationNotFound() {
        var dto = mock(CreateAdRequestDto.class);
        var property = mock(CreatePropertyRequestDto.class);
        var locationId = generateId();

        when(dto.property()).thenReturn(property);
        when(property.locationId()).thenReturn(locationId);
        when(locationService.findById(dto.property().locationId())).thenReturn(Optional.empty());

        assertThrows(BadRequestException.class, () -> service.createAd(dto));
    }

    @Test
    @DisplayName("Create ad - ok, with house")
    void createAd_okWithHouse() {
        var dto = mock(CreateAdRequestDto.class);
        var property = mock(CreatePropertyRequestDto.class);
        var locationId = generateId();
        var location = mock(Location.class);
        var owner = mock(User.class);
        var equipment = Set.of(INTERNET, TV);
        var house = mock(CreateHouseDto.class);
        var ad = mock(Ad.class);

        when(dto.property()).thenReturn(property);
        when(property.locationId()).thenReturn(locationId);
        when(locationService.findById(dto.property().locationId())).thenReturn(Optional.of(location));
        when(userService.findById(1L)).thenReturn(owner);
        when(property.equipment()).thenReturn(equipment);
        when(property.house()).thenReturn(house);
        when(adRepository.save(any())).thenReturn(ad);

        var result = service.createAd(dto);

        assertAll(
                () -> assertThat(result).isEqualTo(ad)
        );
    }

    @Test
    void findAll() {
    }

    @Test
    void findOne() {
    }

    @Test
    void delete() {
    }

    @Test
    void findNewest() {
    }
}
