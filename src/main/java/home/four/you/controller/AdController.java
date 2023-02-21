package home.four.you.controller;

import home.four.you.model.dto.AdBriefDetailsDto;
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
@RequestMapping(value = "ads")
@Slf4j
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    private final ConversionService conversionService;

    @GetMapping
    public Page<AdBriefDetailsDto> findAll(@PageableDefault Pageable pageable) {
        log.debug("Finding ads");

        var ads = adService.findAll(pageable);

        return ads.map(ad -> conversionService.convert(ad, AdBriefDetailsDto.class));
    }

    @GetMapping("/{id}")
    public AdBriefDetailsDto findOne(@PathVariable Long id) {
        log.debug("Finding ad with id {}", id);

        var ad = adService.findOne(id);

        return conversionService.convert(ad, AdBriefDetailsDto.class);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.debug("Deleting an ad {}", id);

        adService.delete(id);
    }

}
