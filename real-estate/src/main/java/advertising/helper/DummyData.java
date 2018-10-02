package advertising.helper;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import advertising.enums.AdOwnerType;
import advertising.enums.HeatType;
import advertising.model.Ad;
import advertising.model.Appartment;
import advertising.model.Equipment;
import advertising.model.House;
import advertising.model.Location;
import advertising.model.Price;
import advertising.model.RealEstate;
import advertising.model.User;
import advertising.repository.AppartmentRepository;
import advertising.repository.HouseRepository;
import advertising.repository.UserRepository;
import advertising.service.AdService;
import advertising.service.EquipmentService;
import advertising.service.LocationService;

@Component
@Profile("test")
public class DummyData {

	private static Random random = new Random();

	@Autowired
	private EquipmentService equipmentService;

	@Autowired
	private LocationService locationService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdService adService;
	
	@PostConstruct
	public void createAds() {
		for (int i = 0; i < 3; i++) {
			createDummyAd();
		}
	}
	
	@Transactional
	public void createDummyAd() {
		RealEstate realEstate = getRealEstateInstance();
		realEstate.setArea(random.nextInt(200) + 10);
		realEstate.setRoomsNumber(random.nextInt(5) + 0.5);
		realEstate.setBooked(booked());
		realEstate.setHeatType(getHeatType());
		realEstate.setEquipment(getEquipmentSet());
		realEstate.setLocation(getLocationInstance());
		
		if(realEstate instanceof House) {
			House house = (House) realEstate;
			house.setFloorsNumber(random.nextInt(4) + 1);
		//	houseRepository.save((House) realEstate);
		} else {
			Appartment appartment = (Appartment) realEstate;
			appartment.setFloor(random.nextInt(11) + 1);
		//	appartmentRepository.save((Appartment) realEstate);
		}

		AdOwnerType type = getAdOwnerType();
		Price price = getPriceInstance(type);

		Ad ad = new Ad();
		ad.setAdOwnerType(type);
		ad.setTitle("Dummy Ad");
		ad.setUser(getUserInstance());
		ad.setPrice(price);
		ad.setRealEstate(realEstate);
		adService.save(ad);
	}

	private RealEstate getRealEstateInstance() {
		RealEstate retVal = null;
		switch (random.nextInt(2) + 1) {
		case 1:
			retVal = new House();
			break;
		case 2:
			retVal = new Appartment();
			break;
		}
		return retVal;
	}

	private Equipment getEquipmentInstance() {
		Equipment retVal = null;
		switch (random.nextInt(3) + 1) {
		case 1:
			retVal = equipmentService.findOne(1L);
			break;
		case 2:
			retVal = equipmentService.findOne(2L);
			break;
		case 3:
			retVal = equipmentService.findOne(3L);
			break;
		case 4:
			retVal = equipmentService.findOne(4L);
			break;
		case 5:
			retVal = equipmentService.findOne(5L);
			break;
		case 6:
			retVal = equipmentService.findOne(6L);
			break;
		}
		return retVal;
	}

	private Set<Equipment> getEquipmentSet() {
		Set<Equipment> retVal = new HashSet<>();
		int iterations = random.nextInt(6) + 1;
	//	for (int i = 0; i < iterations; i++) {
			Equipment e = getEquipmentInstance();
			retVal.add(e);
	//	}
		return retVal;
	}

	private Location getLocationInstance() {
		Location retVal = null;
		switch (random.nextInt(3) + 1) {
		case 1:
			retVal = locationService.findOne(1L);
			break;
		case 2:
			retVal = locationService.findOne(2L);
			break;
		case 3:
			retVal = locationService.findOne(3L);
			break;
		case 4:
			retVal = locationService.findOne(4L);
			break;
		case 5:
			retVal = locationService.findOne(5L);
			break;
		}
		return retVal;
	}

	/*
	 * private void populateLocations() { Location belgrade = new
	 * Location("Belgrade", "11000"); Location noviSad = new
	 * Location("Novi Sad", "21000"); Location subotica = new
	 * Location("Subotica", "24000"); Location nis = new Location("Nis",
	 * "18000"); locationService.save(belgrade);
	 * locationService.save(noviSad); locationService.save(subotica);
	 * locationService.save(nis); }
	 */

	private boolean booked() {
		boolean retVal = false;
		switch (random.nextInt(2) + 1) {
		case 1:
			retVal = false;
			break;
		case 2:
			retVal = true;
			break;
		}
		return retVal;
	}

	private HeatType getHeatType() {
		switch (random.nextInt(HeatType.values().length + 1)) {
		case 1:
			return HeatType.GAS;
		case 2:
			return HeatType.CENTRAL;
		case 3:
			return HeatType.WOOD;
		}
		return null;
	}

	private Price getPriceInstance(AdOwnerType type) {
		Price retVal = new Price();
		retVal.setCurrency(Currency.getInstance("EUR"));
		switch (type) {
		case SELLER:
			retVal.setAmount(new BigDecimal(random.nextInt(200000) + 10000));
			break;
		case BUYER:
			retVal.setAmount(new BigDecimal(random.nextInt(200000) + 10000));
			break;
		case RENTER:
			retVal.setAmount(new BigDecimal(random.nextInt(1500) + 100));
			break;
		case TENANT:
			retVal.setAmount(new BigDecimal(random.nextInt(1500) + 100));
			break;
		}
		return retVal;
	}

	private AdOwnerType getAdOwnerType() {
		switch (random.nextInt(4) + 1) {
		case 1:
			return AdOwnerType.BUYER;
		case 2:
			return AdOwnerType.SELLER;
		case 3:
			return AdOwnerType.RENTER;
		case 4:
			return AdOwnerType.TENANT;
		}
		return null;
	}

	private User getUserInstance() {
		User retVal = null;
		switch (random.nextInt(3) + 1) {
		case 1:
			retVal = userRepository.findOne(1L);
			break;
		case 2:
			retVal = userRepository.findOne(2L);
			break;
		case 3:
			retVal = userRepository.findOne(3L);
			break;
		default:
			break;
		}
		return retVal;
	}

}
