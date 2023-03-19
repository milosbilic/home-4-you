package home.four.you.controller;

import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateAdResponseDto;
import home.four.you.model.entity.Ad;
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

        when(adService.createAd(dto)).thenReturn(ad);
        when(conversionService.convert(ad, CreateAdResponseDto.class)).thenReturn(responseDto);

        var result = controller.createAd(dto);

        assertThat(result).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("Find all")
    void findAll() {
        var pageable = mock(Pageable.class);
        var dto = mock(AdBriefDetailsDto.class);
        var page = new PageImpl<>(List.of(ad));

        when(adService.findAll(pageable)).thenReturn(page);
        when(conversionService.convert(ad, AdBriefDetailsDto.class)).thenReturn(dto);

        var result = controller.findAll(pageable);

        assertThat(result).isEqualTo(new PageImpl<>(List.of(dto)));
    }

    @Test
    @DisplayName("Get details - ok")
    void getDetails_ok() {
        Long id = generateId();
        var dto = mock(AdDetailsDto.class);

        when(adService.findById(id)).thenReturn(ad);

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
}
