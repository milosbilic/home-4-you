package advertising.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import advertising.dto.AdDto;
import advertising.dto.SearchDto;
import advertising.exception.NotFoundException;
import advertising.helper.Helper;
import advertising.helper.converter.ConvertToAdEntity;
import advertising.model.Ad;
import advertising.model.Equipment;
import advertising.model.User;
import advertising.repository.AdRepository;
import advertising.service.AdService;
import advertising.service.EquipmentService;
import advertising.service.UserService;

@Service
@Transactional
public class AdServiceImpl implements AdService {

	@Autowired
	private AdRepository adRepository;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private ConvertToAdEntity toEntity;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Ad> findAll() {
		return adRepository.findAll();
	}

	@Override
	public Ad findOne(Long id) {
		Ad ad = adRepository.findOne(id);
		if (ad == null)
			throw new NotFoundException("Ad with the ID of " + id + " not found.");
		return ad;
	}

	@Override
	public Ad save(Ad ad) {
		ad.setExpirationDate(calculateExpirationDate());
		return adRepository.save(ad);
	}
	
	@Override
	public void save(AdDto adDto, List<Long> equipmentIds, String username) {
		User user = userService.findByUsername(username);
		Set<Equipment> equipment = equipmentService.findByIds(equipmentIds);
		Ad newAd = toEntity.convert(adDto);
		newAd.setUser(user);
		if (newAd.getRealEstate() != null) {
			System.out.println(newAd.getRealEstate());
			System.out.println("nije nal");
		}
		newAd.getRealEstate().setEquipment(equipment);
		adRepository.save(newAd);
	}

	@Override
	public void delete(Ad ad) {
		adRepository.delete(ad);
	}

	// set expiration date to 3 months from creation date
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
	public Page<Ad> search(SearchDto searchDto, Pageable pageable) {
		Page<Ad> retVal = null;
		Class<?> realEstate = Helper.getRealEstateClass(searchDto.getRealEstateType());
		if (searchDto.getLocation() == null || searchDto.getLocation().equals("")) {
			retVal = adRepository.search(searchDto.getAdTypeEnum(), realEstate, new BigDecimal(searchDto.getMinPrice()),
					new BigDecimal(searchDto.getMaxPrice()), searchDto.getMinArea(), 
					searchDto.getMaxArea(), pageable);
		} else { // location has been provided
			retVal = adRepository.search(searchDto.getAdTypeEnum(), searchDto.getLocation(), realEstate,
					new BigDecimal(searchDto.getMinPrice()), new BigDecimal(searchDto.getMaxPrice()),
					searchDto.getMinArea(), searchDto.getMaxArea(), pageable);
		}
		return retVal;
	}
}
