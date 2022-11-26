package home.four.you.service.impl;

import home.four.you.model.entity.Ad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import home.four.you.dto.SearchDto;
import home.four.you.repository.AdRepository;
import home.four.you.service.AdService;
import home.four.you.service.SearchService;

@Service
@Transactional
public class SearchServiceImpl implements SearchService {

	private static final double MIN_VALUE = 0.0;
	private static final double MAX_VALUE = 9999999999.0;
	
	@Autowired
	private AdRepository adRepo;
	
	@Autowired
	private AdService adService;
	
	@Override
	public Page<Ad> search(SearchDto searchDto, Pageable pageable) {
		doNullChecks(searchDto);
		convertStringsToEnums(searchDto);
		return adService.search(searchDto, pageable);
	}
	
	private void doNullChecks(SearchDto searchDto) {
		if (searchDto.getMinArea() == null)
			searchDto.setMinArea(MIN_VALUE);
		if (searchDto.getMinPrice() == null)
			searchDto.setMinPrice(MIN_VALUE);
		if (searchDto.getMaxArea() == null)
			searchDto.setMaxArea(MAX_VALUE);
		if (searchDto.getMaxPrice() == null)
			searchDto.setMaxPrice(MAX_VALUE);
	}
	
	private void convertStringsToEnums(SearchDto searchDto) {
		searchDto.setAdTypeEnum(searchDto.getAdType());
		searchDto.setRealEstateClass(searchDto.getRealEstateType());
	}
}
