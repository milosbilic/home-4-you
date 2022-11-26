package home.four.you.repository;

import home.four.you.model.AdType;
import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;


public interface AdRepository extends JpaRepository<Ad, Long> {

    List<Ad> findTop3ByOrderByDateCreatedDesc();

    @Query("SELECT a FROM Ad a JOIN a.realEstate re WHERE a.adType = :adType "
            + "AND TYPE(re) IN (:realEstate) AND re.location.name = :location "
            + "AND a.price.amount >= :minPrice AND a.price.amount <= :maxPrice "
            + "AND a.realEstate.area >= :minArea AND a.realEstate.area <= :maxArea")
    Page<Ad> search(@Param("adType") AdType adType,
                    @Param("location") String location,
                    @Param("realEstate") Class<?> realEstate,
                    @Param("minPrice") BigDecimal minPrice,
                    @Param("maxPrice") BigDecimal maxPrice,
                    @Param("minArea") double minArea,
                    @Param("maxArea") double maxArea, Pageable pageable);


    @Query("SELECT a FROM Ad a JOIN a.realEstate re WHERE a.adType = :adType "
            + "AND TYPE(re) IN (:realEstate) "
            + "AND a.price.amount >= :minPrice AND a.price.amount <= :maxPrice "
            + "AND a.realEstate.area >= :minArea AND a.realEstate.area <= :maxArea")
    Page<Ad> search(@Param("adType") AdType adType,
                    @Param("realEstate") Class<?> realEstate,
                    @Param("minPrice") BigDecimal minPrice,
                    @Param("maxPrice") BigDecimal maxPrice,
                    @Param("minArea") double minArea,
                    @Param("maxArea") double maxArea, Pageable pageable);
}
