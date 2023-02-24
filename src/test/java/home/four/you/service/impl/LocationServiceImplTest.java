package home.four.you.service.impl;

import home.four.you.model.entity.Location;
import home.four.you.repository.LocationRepository;
import home.four.you.service.LocationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static home.four.you.TestUtil.generateId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link LocationServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
class LocationServiceImplTest {

    LocationService service;

    @Mock
    LocationRepository locationRepository;

    @BeforeEach
    void setUp() {
        service = new LocationServiceImpl(locationRepository);
    }

    @Test
    @DisplayName("Find by ID - ok")
    void findById_ok() {
        Long id = generateId();
        var location = mock(Location.class);

        when(locationRepository.findById(id)).thenReturn(Optional.of(location));

        Optional<Location> result = service.findById(id);

        Assertions.assertThat(result).isEqualTo(Optional.of(location));
    }
}
