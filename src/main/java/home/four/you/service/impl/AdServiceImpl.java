package home.four.you.service.impl;

import home.four.you.exception.NotFoundException;
import home.four.you.helper.Helper;
import home.four.you.model.dto.AdDto;
import home.four.you.model.dto.SearchDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Equipment;
import home.four.you.model.entity.Location;
import home.four.you.model.entity.User;
import home.four.you.repository.AdRepository;
import home.four.you.service.AdService;
import home.four.you.service.EquipmentService;
import home.four.you.service.LocationService;
import home.four.you.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Implementation of {@link AdService}.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AdServiceImpl implements AdService {

    private final AdRepository adRepository;

    private final EquipmentService equipmentService;

    private final UserService userService;

    private final LocationService locationService;

    @Override
    public List<Ad> findAll() {
        log.debug("Finding all ads...");

        return adRepository.findAll();
    }

    @Override
    public Ad findOne(Long id) {
        log.debug("Finding ad with id {}", id);

        return adRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ad with the ID of " + id + " not found,"));
    }

    @Override
    public Ad save(Ad ad) {
        log.debug("Saving ad [{}]", ad);

        ad.setExpirationDate(calculateExpirationDate());
        return adRepository.save(ad);
    }

    @Override
    public Ad save(AdDto adDto, List<Long> equipmentIds, String username) throws IOException {
        log.debug("Saving ad [{}]", adDto);

        User user = userService.findByUsername(username);
        Set<Equipment> equipment = null;
        Location location = null;
//		Ad newAd = toEntity.convert(adDto);
        var newAd = new Ad();

        if (equipmentIds != null)
            equipment = equipmentService.findByIds(equipmentIds);

        String locationName = newAd.getProperty().getLocation().getName();
        //retrieve location entity or create a new one
        Optional<Location> locationOptional = locationService.findByName(locationName);
        if (!locationOptional.isPresent())
            location = new Location();
        else
            location = locationOptional.get();
        newAd.setUser(user);
        newAd.getProperty().setEquipment(equipment);
        newAd.getProperty().setLocation(location);
        newAd.setExpirationDate(calculateExpirationDate());
        newAd.getProperty().setImage(adDto.getFile().getFile().getBytes());

//		return adRepository.save(newAd);
        return newAd;
    }

    @Override
    public void delete(Ad ad) {
        log.debug("Deleting ad [{}]", ad);

        adRepository.delete(ad);
    }

    @Override
    public List<Ad> findNewest() {
        log.debug("Finding newest ads...");

        return adRepository.findTop3ByOrderByDateCreatedDesc();
    }

    @Override
    public Page<Ad> search(SearchDto searchDto, Pageable pageable) {
        log.debug("Searching for ads with criteria [{}]", searchDto);

        Page<Ad> retVal = null;
        Class<?> realEstate = Helper.getRealEstateClass(searchDto.getRealEstateType());
//		if (searchDto.getLocation() == null || searchDto.getLocation().equals("")) {
//			retVal = adRepository.search(searchDto.getAdTypeEnum(), realEstate, new BigDecimal(searchDto.getMinPrice()),
//					new BigDecimal(searchDto.getMaxPrice()), searchDto.getMinArea(),
//					searchDto.getMaxArea(), pageable);
//		} else { // location has been provided
//			retVal = adRepository.search(searchDto.getAdTypeEnum(), searchDto.getLocation(), realEstate,
//					new BigDecimal(searchDto.getMinPrice()), new BigDecimal(searchDto.getMaxPrice()),
//					searchDto.getMinArea(), searchDto.getMaxArea(), pageable);
//		}
        return retVal;
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
}
