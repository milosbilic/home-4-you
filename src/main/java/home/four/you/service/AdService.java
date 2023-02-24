package home.four.you.service;

import home.four.you.model.dto.CreateAdRequestDto;
import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service for {@link Ad} entity related operations.
 */
public interface AdService {

    /**
     * Finds all ads.
     *
     * @return List of all ads.
     */
    Page<Ad> findAll(Pageable pageable);

    /**
     * Finds an ad by provided ID.
     *
     * @param id Ad ID.
     * @return Ad.
     */
    Ad findById(Long id);

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
    List<Ad> findNewest();

    Ad createAd(CreateAdRequestDto dto);
}
