package home.four.you.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import home.four.you.model.entity.Property;

@NoRepositoryBean
public interface PropertyRepository<T extends Property> extends JpaRepository<T, Long> {

	@Query("select p from Property p where type(p) = 'House'")
	List<Property> getHouses();
}
