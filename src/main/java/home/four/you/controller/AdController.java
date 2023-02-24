package home.four.you.controller;

import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateAdResponseDto;
import home.four.you.model.entity.Ad;
import home.four.you.service.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for {@link Ad} related operations.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "ads")
public class AdController {

    private final AdService adService;
    private final ConversionService conversionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CreateAdResponseDto createAd(@RequestBody @Valid CreateAdRequestDto dto) {
        log.debug("Creating ad [{}]", dto);

        var ad = adService.createAd(dto);

        return conversionService.convert(ad, CreateAdResponseDto.class);
    }

    @GetMapping
    public Page<AdBriefDetailsDto> findAll(@PageableDefault Pageable pageable) {
        log.debug("Finding ads");

        return adService.findAll(pageable)
                .map(ad -> conversionService.convert(ad, AdBriefDetailsDto.class));
    }

    @GetMapping("{id}")
    public AdDetailsDto getDetails(@PathVariable Long id) {
        log.debug("Finding ad with id {}", id);

        var ad = adService.findById(id);

        return conversionService.convert(ad, AdDetailsDto.class);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.debug("Deleting an ad {}", id);

        adService.delete(id);
    }

}
