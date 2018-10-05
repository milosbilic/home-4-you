package advertising.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import advertising.dto.SearchDto;
import advertising.model.Ad;
import advertising.service.AdService;
import advertising.service.SearchService;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	@Autowired
	private AdService adService;
	
	@Override
	public List<Ad> search(SearchDto searchDto) {
		
		return null;
	}

}
