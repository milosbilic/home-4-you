package advertising.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import advertising.dto.SearchDto;
import advertising.model.Ad;

public interface AdService {

	List<Ad> findAll();
	
	Ad findOne(Long id);
	
	Ad save(Ad ad);
	
	void delete(Ad ad);
	
	List<Ad> findNewest();

	Page<Ad> search(SearchDto searchDto, Pageable pageable);
	
}
