package home.four.you.controller;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdDetailsDto;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.dto.CreateAdResponseDto;
import home.four.you.model.entity.Ad;
import home.four.you.security.CurrentUser;
import home.four.you.security.UserPrincipal;
import home.four.you.security.auth.authorization.permission.CanDeleteAd;
import home.four.you.service.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public CreateAdResponseDto createAd(@RequestBody @Valid CreateAdRequestDto dto,
                                        @CurrentUser UserPrincipal caller) {
        log.info("Creating ad [{}]", dto);

        var ad = adService.createAd(dto, caller);

        return conversionService.convert(ad, CreateAdResponseDto.class);
    }

    @GetMapping
    public Page<AdBriefDetailsDto> findAll(@PageableDefault Pageable pageable) {
        log.info("Finding ads");

        return adService.findAll(pageable)
                .map(ad -> conversionService.convert(ad, AdBriefDetailsDto.class));
    }

    @GetMapping("{id}")
    public AdDetailsDto getDetails(@PathVariable Long id) {
        log.info("Finding ad with id {}", id);

        var ad = adService.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return conversionService.convert(ad, AdDetailsDto.class);
    }

    @DeleteMapping("{id}")
    @CanDeleteAd
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deleting ad {}", id);

        adService.delete(id);
    }

    @GetMapping("latest")
    public List<AdBriefDetailsDto> getLatest() {
        log.info("Getting latest ads");

        return adService.findLatest().stream()
                .map(ad -> conversionService.convert(ad, AdBriefDetailsDto.class))
                .toList();
    }

}
