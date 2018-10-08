package advertising.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import advertising.dto.SearchDto;
import advertising.model.Ad;
import advertising.model.Apartment;
import advertising.model.House;
import advertising.repository.AdRepository;
import advertising.service.AdService;

@Service
@Transactional
public class AdServiceImpl implements AdService {

	@Autowired
	private AdRepository adRepository;
	
	@Override
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	@Override
	public Ad findOne(Long id) {
		return adRepository.findOne(id);
	}

	@Override
	public Ad save(Ad ad) {
		ad.setExpirationDate(calculateExpirationDate());
		return adRepository.save(ad);
	}

	@Override
	public void delete(Ad ad) {
		adRepository.delete(ad);
	}
	
	//set expiration date to 3 months from creation date
	private Date calculateExpirationDate() {
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		c.add(Calendar.MONTH, 3);
		Date expirationDate = c.getTime();
		return expirationDate;
	}

	@Override
	public List<Ad> findNewest() {
		return adRepository.findTop3ByOrderByDateCreatedDesc();
	}
	
	@Override
	public List<Ad> search(SearchDto searchDto) {
		System.out.println(searchDto);
		List<Ad> retVal = null;
		if (searchDto.getRealEstateClass().getSimpleName().equalsIgnoreCase("House")) {
			retVal = adRepository.search(searchDto.getAdTypeEnum(),
					searchDto.getLocation(), House.class, new BigDecimal(searchDto.getMinPrice()), 
					new BigDecimal(searchDto.getMaxPrice()), searchDto.getMinArea(), 
					searchDto.getMaxArea());
		} else {
			retVal = adRepository.search(searchDto.getAdTypeEnum(),
					searchDto.getLocation(), Apartment.class, new BigDecimal(searchDto.getMinPrice()), 
					new BigDecimal(searchDto.getMaxPrice()), searchDto.getMinArea(), 
					searchDto.getMaxArea());
		}
		return retVal;
	}
}
