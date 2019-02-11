package advertising.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import advertising.dto.SearchDto;
import advertising.model.Ad;

public interface SearchService {

	Page<Ad> search(SearchDto searchDto, Pageable pageable);	
}
