package home.four.you.repository;


import home.four.you.model.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JPA repository for {@link House} entity.
 */
public interface HouseRepository extends JpaRepository<House, Long> {

}
