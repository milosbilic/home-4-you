package home.four.you.service;

import java.io.IOException;
import java.util.List;

import home.four.you.dto.SearchDto;
import home.four.you.model.entity.Ad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import home.four.you.dto.AdDto;

public interface AdService {

	List<Ad> findAll();
	
	Ad findOne(Long id);
	
	Ad save(Ad ad);
	
	Ad save(AdDto adDto, List<Long> equipmentIds, String username) throws IOException;
	
	void delete(Ad ad);
	
	List<Ad> findNewest();

	Page<Ad> search(SearchDto searchDto, Pageable pageable);


	
}
