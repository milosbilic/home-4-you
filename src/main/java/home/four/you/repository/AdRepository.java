package home.four.you.repository;

import home.four.you.model.PropertyType;
import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

import static java.util.Optional.ofNullable;

/**
 * JPA repository for {@link Ad} entity.
 */
public interface AdRepository extends JpaRepository<Ad, Long>, JpaSpecificationExecutor<Ad> {

    /**
     * JPA specifications for {@link Ad} entity.
     */
    interface Specs {

        static Specification<Ad> byType(Ad.Type adType) {
            return (root, query, criteriaBuilder) ->
                ofNullable(adType)
                    .map(type -> criteriaBuilder.equal(root.get("type"), type))
                    .orElse(criteriaBuilder.conjunction());
        }

        static Specification<Ad> byPriceGreaterThanOrEqual(Integer minPrice) {
            return (root, query, criteriaBuilder) ->
                ofNullable(minPrice)
                    .map(price -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price))
                    .orElse(criteriaBuilder.conjunction());
        }

        static Specification<Ad> byPriceLessThanOrEqual(Integer maxPrice) {
            return (root, query, criteriaBuilder) ->
                ofNullable(maxPrice)
                    .map(price -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price))
                    .orElse(criteriaBuilder.conjunction());
        }

        static Specification<Ad> byPropertyType(PropertyType propertyType) {
            return (root, query, criteriaBuilder) -> {
                if (propertyType != null) {
                    if (PropertyType.HOUSE.equals(propertyType)) {
                        return criteriaBuilder.isNotNull(root.get("property").get("house"));
                    } else {
                        return criteriaBuilder.isNotNull(root.get("property").get("apartment"));
                    }
                }
                return criteriaBuilder.conjunction();
            };
        }

        static Specification<Ad> byAreaGreaterThanOrEqual(Integer minArea) {
            return (root, query, criteriaBuilder) ->
                ofNullable(minArea)
                    .map(area -> criteriaBuilder.greaterThanOrEqualTo(root.get("area"), area))
                    .orElse(criteriaBuilder.conjunction());
        }

        static Specification<Ad> byAreaLessThanOrEqual(Integer maxArea) {
            return (root, query, criteriaBuilder) ->
                ofNullable(maxArea)
                    .map(area -> criteriaBuilder.lessThanOrEqualTo(root.get("area"), area))
                    .orElse(criteriaBuilder.conjunction());
        }

        static Specification<Ad> byNumberOfRoomsGreaterThanOrEqual(Integer minNumberOfRooms) {
            return (root, query, criteriaBuilder) ->
                ofNullable(minNumberOfRooms)
                    .map(rooms -> criteriaBuilder.greaterThanOrEqualTo(root.get("property").get("numberOfRooms"), rooms))
                    .orElse(criteriaBuilder.conjunction());
        }

        static Specification<Ad> byNumberOfRoomsLessThanOrEqual(Integer maxNumberOfRooms) {
            return (root, query, criteriaBuilder) ->
                ofNullable(maxNumberOfRooms)
                    .map(rooms -> criteriaBuilder.lessThanOrEqualTo(root.get("property").get("numberOfRooms"), rooms))
                    .orElse(criteriaBuilder.conjunction());
        }
    }

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
