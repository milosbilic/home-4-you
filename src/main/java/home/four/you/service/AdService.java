package home.four.you.service;

import home.four.you.model.dto.AdDto;
import home.four.you.model.dto.SearchDto;
import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
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
    Ad findOne(Long id);

    /**
     * Saves an ad to database.
     *
     * @param ad Ad.
     * @return Persisted ad.
     */
    Ad save(Ad ad);

    Ad save(AdDto adDto, List<Long> equipmentIds, String username) throws IOException;

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

    Page<Ad> search(SearchDto searchDto, Pageable pageable);


}
