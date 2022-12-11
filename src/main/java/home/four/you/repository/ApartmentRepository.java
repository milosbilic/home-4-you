package home.four.you.repository;

import home.four.you.model.entity.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link Apartment} entity.
 */
public interface ApartmentRepository extends JpaRepository<Apartment, Long> {

}
