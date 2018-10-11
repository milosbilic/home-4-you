package advertising.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import advertising.enums.AdType;
import advertising.model.Ad;

@Repository
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
	
	@Query("SELECT a FROM Ad a JOIN a.realEstate re WHERE TYPE(re) IN (:name)")
	List<Ad> test(@Param("name") Class<?> re);
}
