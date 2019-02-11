package advertising.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import advertising.dto.AdDto;
import advertising.dto.SearchDto;
import advertising.model.Ad;

public interface AdService {

	List<Ad> findAll();
	
	Ad findOne(Long id);
	
	Ad save(Ad ad);
	
	Ad save(AdDto adDto, List<Long> equipmentIds, String username) throws IOException;
	
	void delete(Ad ad);
	
	List<Ad> findNewest();

	Page<Ad> search(SearchDto searchDto, Pageable pageable);


	
}
