package home.four.you.service;

import home.four.you.model.dto.AdBriefDetailsDto;
import home.four.you.model.dto.AdSearchFilter;
import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.entity.Ad;
import home.four.you.security.UserPrincipal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service for {@link Ad} entity related operations.
 */
public interface AdService {

    /**
     * Finds an ad by provided ID.
     *
     * @param id Ad ID.
     * @return Ad.
     */
    Optional<Ad> findById(Long id);

    /**
     * Deletes an ad by provided ID.
     *
     * @param id Ad ID.
     */
    void delete(Long id);

    /**
     * Finds the most recent ads.
     *
     * @return Most recent ads.
     */
    List<Ad> findLatest();

    /**
     * Creates an ad with specified details and sets the caller user as ad's owner.
     *
     * @param dto    Ad create request DTO.
     * @param caller User posting ad.
     * @return Created ad.
     */
    Ad createAd(CreateAdRequestDto dto, UserPrincipal caller);

    /**
     * Searches ads with specified filter and paging.
     *
     * @param filter Ad search filter.
     * @param pageable Pageable.
     * @return Page of matching ads.
     */
    Page<Ad> search(AdSearchFilter filter, Pageable pageable);

    /**
     * Marks outdated ads as expired.
     */
    void markOutdatedAsExpired();
}
