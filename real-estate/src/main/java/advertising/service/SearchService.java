package advertising.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import advertising.dto.SearchDto;
import advertising.model.Ad;

public interface SearchService {


	List<Ad> test();

	Page<Ad> search(SearchDto searchDto, Pageable pageable);
	
}
