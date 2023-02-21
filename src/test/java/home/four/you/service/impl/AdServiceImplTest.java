package home.four.you.service.impl;

import home.four.you.model.dto.AdDto;
import home.four.you.model.dto.FileBucket;
import home.four.you.model.dto.HouseAdDto;
import home.four.you.model.entity.Ad;
import home.four.you.model.entity.House;
import home.four.you.model.entity.Location;
import home.four.you.model.entity.User;
import home.four.you.repository.AdRepository;
import home.four.you.service.EquipmentService;
import home.four.you.service.LocationService;
import home.four.you.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.*;

import static org.mockito.Mockito.*;

public class AdServiceImplTest {

	@Mock
	AdRepository adRepository;
	
	@Mock
	UserService userService;
	
	@Mock
	EquipmentService equipmentService;
	
	@Mock
	LocationService locationService;
	
	@InjectMocks
	AdServiceImpl adServiceImpl;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAll() {
		List<Ad> ads = new ArrayList<>(Arrays.asList(new Ad(), new Ad()));
		when(adRepository.findAll()).thenReturn(ads);
		
//		List<Ad> result = adServiceImpl.findAll();
		
//		assertEquals(ads, result);
	}

	@Test
	public void testFindOne() {
		Ad ad = new Ad();
		when(adRepository.findById(1L)).thenReturn(Optional.of(ad));
		
		Ad result = adServiceImpl.findOne(1L);
		
//		assertEquals(ad, result);
	}
	
	@Test
	public void whenFindingNonExistingAd_thenShouldThrowException() {
		Long nonExistingId = 1L;
		when(adRepository.findById(1L)).thenReturn(Optional.empty());
		
		Ad nonExisting = adServiceImpl.findOne(nonExistingId);
//		assertNull(nonExisting);
	}

	@Test
	public void testSaveAd() {
		Ad ad = new Ad();
		when(adRepository.save(ad)).thenReturn(ad);
		
		
		Ad result = adServiceImpl.save(ad);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 3);
//		assertEquals(ad.getExpirationDate().getMonth(), c.getTime().getMonth());
//		assertEquals(ad, result);
	}

	@Test
	public void testSaveAdDtoListOfLongString() throws IOException {
		//given
		String username = "user";
		User u = new User();
		AdDto dto = new HouseAdDto();
		Ad converted = new Ad();
		House h = new House();
//		h.setLocation(new Location());
//		converted.setProperty(h);
		List<Long> equipmentIds = Arrays.asList(1L, 2L, 3L);
		FileBucket bucket = new FileBucket();
		bucket.setFile(new MockMultipartFile("image.jpg", new byte[3]));
		dto.setFile(bucket);
		
		when(userService.findByEmail(username)).thenReturn(u);
		when(equipmentService.findByIds(new ArrayList<>())).thenReturn(new HashSet<>());
//		when(locationService.findByName(h.getLocation().getName()))
//		.thenReturn(Optional.of(new Location()));
		when(adRepository.save(converted)).thenReturn(converted);
		
		//when
		Ad result = adServiceImpl.save(dto, equipmentIds, username);
		
		//then
//		assertEquals(converted.getAdType(), result.getAdType());
//		assertEquals(converted.getDateCreated(), result.getDateCreated());
//		assertEquals(converted.getDescription(), result.getDescription());
//		assertEquals(converted.getExpirationDate(), result.getExpirationDate());
//		assertEquals(converted.getId(), result.getId());
//		assertEquals(converted.getPrice(), result.getPrice());
//		assertEquals(converted.getRealEstate(), result.getRealEstate());
//		assertEquals(converted.getTitle(), result.getTitle());
//		assertEquals(converted.getUser(), result.getUser());
	}


	@Test
	public void testFindNewest() {
		List<Ad> newest = Arrays.asList(new Ad(), new Ad(), new Ad());
		
		when(adRepository.findTop3ByOrderByDateCreatedDesc()).thenReturn(newest);
		
		List<Ad> result = adServiceImpl.findNewest();
//		assertNotNull(result);
//		assertEquals(3, result.size());
	}

/*	@Test
	public void testSearchWithoutLocation() {
		//given
		Location belgrade = new Location("Belgrade");
		Location noviSad = new Location("Novi Sad");
		Location ruma = new Location("Ruma");
		
		Ad belgradeAd = new Ad();
		House belgradeHouse = new House();
		belgradeHouse.setLocation(belgrade);
		belgradeAd.setRealEstate(belgradeHouse);
		
		Ad noviSadAd = new Ad();
		House noviSadHouse = new House();
		noviSadHouse.setLocation(noviSad);
		noviSadAd.setRealEstate(noviSadHouse);
		
		Ad rumaAd = new Ad();
		House rumaHouse = new House();
		rumaHouse.setLocation(ruma);
		rumaAd.setRealEstate(rumaHouse);
		
		Pageable page = new PageRequest(1, 10);
		
		Page<Ad> ads = new PageImpl<>(Arrays.asList(belgradeAd, noviSadAd, rumaAd));
		SearchDto search = new SearchDto(); //null location name
		double minPrice = 0.0;
		double maxPrice = 30.0;
		double minArea = minPrice;
		double maxArea = maxPrice;
		search.setAdTypeEnum(AdType.RENT);
		search.setRealEstateType("house");
		search.setMinPrice(minPrice);
		search.setMaxPrice(minPrice);
		search.setMinArea(minArea);
		search.setMaxArea(maxArea);
		Class<?> realEstate = Helper.getRealEstateClass(search.getRealEstateType());
		
//		when(adRepository.search(search.getAdTypeEnum(), search.getRealEstateClass(),
//				search.getMinPrice(), search.getMaxPrice()), minArea, maxArea, page))
//		.thenReturn(ads);

		
		//when
		Page<Ad> result = adServiceImpl.search(search, page);
		
		//then
		assertEquals(3, result.getContent().size());
		assertEquals("Belgrade", result.getContent().get(0).getRealEstate().getLocation().getName());
	}*/

}
