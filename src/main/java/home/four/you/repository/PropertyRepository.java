package home.four.you.repository;

import home.four.you.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * JPA repository for {@link Property} entity.
 *
 * @param <T> Property type.
 */
@NoRepositoryBean
public interface PropertyRepository<T extends Property> extends JpaRepository<T, Long> {

    /**
     * Gets all properties of type House.
     *
     * @return List of matching properties.
     */
    @Query("select p from Property p where type(p) = 'House'")
    List<Property> getHouses();
}
