package home.four.you.repository;

import home.four.you.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * JPA repository for {@link Property} entity.
 */
public interface PropertyRepository extends JpaRepository<Property, Long> {

    /**
     * Gets all properties of type House.
     *
     * @return List of matching properties.
     */
    @Query("select p from Property p where p.house is not null")
    List<Property> getHouses();
}
