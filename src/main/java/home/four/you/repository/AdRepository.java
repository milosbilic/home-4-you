package home.four.you.repository;

import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository for {@link Ad} entity.
 */
public interface AdRepository extends JpaRepository<Ad, Long> {

    /**
     * Finds top 3 ads ordered by date created in descending order.
     *
     * @return List of matching ads.
     */
    List<Ad> findTop3ByOrderByCreatedAtDesc();

    /**
     * Finds all ads.
     *
     * @param pageable the pageable to request a paged result, can be {@link Pageable#unpaged()}, must not be
     *                 {@literal null}.
     * @return Page of all ads.
     */
    Page<Ad> findAll(Pageable pageable);
}
