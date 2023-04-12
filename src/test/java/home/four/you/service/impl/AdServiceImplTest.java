package home.four.you.service.impl;

import home.four.you.exception.BadRequestException;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateApartmentDto;
import home.four.you.model.dto.CreateHouseDto;
import home.four.you.model.dto.CreatePropertyRequestDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Location;
import home.four.you.model.entity.User;
import home.four.you.repository.AdRepository;
import home.four.you.security.UserPrincipal;
import home.four.you.service.AdService;
import home.four.you.service.LocationService;
import home.four.you.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
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
    UserService userService;

    @Mock
    Ad ad;

    @Mock
    UserPrincipal caller;

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

        assertThrows(BadRequestException.class, () -> service.createAd(dto, caller));
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

        when(dto.property()).thenReturn(property);
        when(property.locationId()).thenReturn(locationId);
        when(locationService.findById(dto.property().locationId())).thenReturn(Optional.of(location));
        when(userService.getById(caller.getId())).thenReturn(owner);
        when(property.equipment()).thenReturn(equipment);
        when(property.house()).thenReturn(house);
        when(adRepository.save(any())).thenReturn(ad);

        var result = service.createAd(dto, caller);

        assertAll(
                () -> assertThat(result).isEqualTo(ad),
                () -> verifyNoMoreInteractions(adRepository),
                () -> verifyNoMoreInteractions(userService)
        );
    }

    @Test
    @DisplayName("Create ad - ok, with apartment")
    void createAd_okWithApartment() {
        var dto = mock(CreateAdRequestDto.class);
        var property = mock(CreatePropertyRequestDto.class);
        var locationId = generateId();
        var location = mock(Location.class);
        var owner = mock(User.class);
        var equipment = Set.of(INTERNET, TV);
        var apartment = mock(CreateApartmentDto.class);

        when(dto.property()).thenReturn(property);
        when(property.locationId()).thenReturn(locationId);
        when(locationService.findById(dto.property().locationId())).thenReturn(Optional.of(location));
        when(userService.getById(caller.getId())).thenReturn(owner);
        when(property.equipment()).thenReturn(equipment);
        when(property.apartment()).thenReturn(apartment);
        when(adRepository.save(any())).thenReturn(ad);

        var result = service.createAd(dto, caller);

        assertAll(
                () -> assertThat(result).isEqualTo(ad),
                () -> verifyNoMoreInteractions(adRepository),
                () -> verifyNoMoreInteractions(userService)
        );
    }

    @Test
    @DisplayName("Find all")
    void findAll() {
        var pageable = mock(Pageable.class);
        PageImpl<Ad> ads = new PageImpl<>(List.of(ad));

        when(service.findAll(pageable)).thenReturn(ads);

        var result = service.findAll(pageable);

        assertThat(result).isEqualTo(ads);
        verifyNoMoreInteractions(adRepository);
    }

    @Test
    @DisplayName("Find by ID - ok, found")
    void findById_okFound() {
        Long id = generateId();

        when(adRepository.findById(id)).thenReturn(Optional.of(ad));

        var result = service.findById(id);

        assertThat(result).hasValue(ad);
    }

    @Test
    @DisplayName("Delete - ok, found and deleted")
    void delete_okFoundAndDeleted() {
        Long id = generateId();

        service.delete(id);

        verify(adRepository, times(1)).deleteById(id);
        verifyNoMoreInteractions(adRepository);
    }

    @Test
    @DisplayName("Find latest - ok")
    void findLatest_ok() {
        var ads = List.of(ad, ad, ad);

        when(adRepository.findTop3ByOrderByCreatedAtDesc()).thenReturn(ads);

        var result = service.findLatest();

        assertThat(result).isEqualTo(ads);
    }
}
