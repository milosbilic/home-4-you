package home.four.you.controller;

import home.four.you.model.PropertyType;
import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.dto.AdSearchFilter;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateAdResponseDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.User;
import home.four.you.security.UserPrincipal;
import home.four.you.service.AdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static home.four.you.TestUtil.generateId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AdController}.
 */
@ExtendWith(MockitoExtension.class)
class AdControllerTest {

    AdController controller;

    @Mock
    AdService adService;

    @Mock
    ConversionService conversionService;

    @Mock
    Ad ad;

    @BeforeEach
    public void setUp() {
        controller = new AdController(adService, conversionService);
    }

    @Test
    @DisplayName("Create ad - ok")
    void createAd_ok() {
        var dto = mock(CreateAdRequestDto.class);
        var responseDto = mock(CreateAdResponseDto.class);
        var caller = mock(UserPrincipal.class);

        when(adService.createAd(dto, caller)).thenReturn(ad);
        when(conversionService.convert(ad, CreateAdResponseDto.class)).thenReturn(responseDto);

        var result = controller.createAd(dto, caller);

        assertThat(result).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("Search")
    void search() {
        var pageable = mock(Pageable.class);
        var dto = mock(AdBriefDetailsDto.class);
        var page = new PageImpl<>(List.of(ad));

        when(adService.search(any(), eq(pageable))).thenReturn(page);
        when(conversionService.convert(ad, AdBriefDetailsDto.class)).thenReturn(dto);

        var result = controller.search(Ad.Type.RENT,
            generateId().intValue(),
            generateId().intValue(),
            PropertyType.HOUSE,
            generateId().intValue(),
            generateId().intValue(),
            generateId().intValue(),
            generateId().intValue(),
            pageable);

        assertThat(result).isEqualTo(new PageImpl<>(List.of(dto)));
    }

    @Test
    @DisplayName("Get details - ok")
    void getDetails_ok() {
        Long id = generateId();
        var dto = mock(AdDetailsDto.class);

        when(adService.findById(id)).thenReturn(Optional.of(ad));

        when(conversionService.convert(ad, AdDetailsDto.class)).thenReturn(dto);

        var result = controller.getDetails(id);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    @DisplayName("Delete")
    void delete() {
        Long id = generateId();

        controller.delete(id);

        verify(adService, times(1)).delete(id);
        verifyNoMoreInteractions(adService);
    }

    @Test
    @DisplayName("Find latest")
    void findLatest() {
        var dto = mock(AdBriefDetailsDto.class);

        when(adService.findLatest()).thenReturn(List.of(ad, ad, ad));
        when(conversionService.convert(ad, AdBriefDetailsDto.class)).thenReturn(dto);

        var result = controller.getLatest();

        assertThat(result).isEqualTo(List.of(dto, dto, dto));
    }
}
