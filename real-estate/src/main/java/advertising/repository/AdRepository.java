package advertising.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import advertising.model.Ad;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {

	List<Ad> findTop3ByOrderByDateCreatedDesc();
	
}
