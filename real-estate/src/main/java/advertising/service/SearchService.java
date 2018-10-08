package advertising.service;

import java.util.List;

import advertising.dto.SearchDto;
import advertising.model.Ad;

public interface SearchService {

	List<Ad> search(SearchDto searchDto);

	List<Ad> test();
	
}
