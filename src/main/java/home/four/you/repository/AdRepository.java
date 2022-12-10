package home.four.you.repository;

import home.four.you.model.entity.Ad;
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
    List<Ad> findTop3ByOrderByDateCreatedDesc();

//    @Query("SELECT a FROM Ad a JOIN a.property p WHERE a.type = :adType "
//            + "AND TYPE(re) IN (:realEstate) AND p.location.name = :location "
//            + "AND a.price.amount >= :minPrice AND a.price.amount <= :maxPrice "
//            + "AND a.property.area >= :minArea AND a.property.area <= :maxArea")
//    Page<Ad> search(@Param("adType") Ad.Type adType,
//                    @Param("location") String location,
//                    @Param("realEstate") Class<?> realEstate,
//                    @Param("minPrice") BigDecimal minPrice,
//                    @Param("maxPrice") BigDecimal maxPrice,
//                    @Param("minArea") double minArea,
//                    @Param("maxArea") double maxArea, Pageable pageable);
//
//
//    @Query("SELECT a FROM Ad a JOIN a.property p WHERE a.type = :adType "
//            + "AND TYPE(p) IN (:realEstate) "
//            + "AND a.price.amount >= :minPrice AND a.price.amount <= :maxPrice "
//            + "AND a.property.area >= :minArea AND a.property.area <= :maxArea")
//    Page<Ad> search(@Param("adType") Ad.Type adType,
//                    @Param("realEstate") Class<?> realEstate,
//                    @Param("minPrice") BigDecimal minPrice,
//                    @Param("maxPrice") BigDecimal maxPrice,
//                    @Param("minArea") double minArea,
//                    @Param("maxArea") double maxArea, Pageable pageable);
}
