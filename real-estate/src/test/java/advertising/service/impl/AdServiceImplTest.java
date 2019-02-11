package advertising.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import advertising.dto.AdDto;
import advertising.dto.FileBucket;
import advertising.dto.HouseAdDto;
import advertising.dto.SearchDto;
import advertising.enums.AdType;
import advertising.exception.NotFoundException;
import advertising.helper.Helper;
import advertising.helper.converter.ConvertToAdEntity;
import advertising.model.Ad;
import advertising.model.House;
import advertising.model.Location;
import advertising.model.User;
import advertising.repository.AdRepository;
import advertising.service.EquipmentService;
import advertising.service.LocationService;
import advertising.service.UserService;

public class AdServiceImplTest {

	@Mock
	AdRepository adRepository;
	
	@Mock
	UserService userService;
	
	@Mock
	ConvertToAdEntity toEntity;
	
	@Mock
	EquipmentService equipmentService;
	
	@Mock
	LocationService locationService;
	
	@InjectMocks
	AdServiceImpl adServiceImpl;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testFindAll() {
		List<Ad> ads = new ArrayList<>(Arrays.asList(new Ad(), new Ad()));
		when(adRepository.findAll()).thenReturn(ads);
		
		List<Ad> result = adServiceImpl.findAll();
		
		assertEquals(ads, result);
	}

	@Test
	public void testFindOne() {
		Ad ad = new Ad();
		when(adRepository.findOne(1L)).thenReturn(ad);
		
		Ad result = adServiceImpl.findOne(1L);
		
		assertEquals(ad, result);
	}
	
	@Test(expected = NotFoundException.class)
	public void whenFindingNonExistingAd_thenShouldThrowException() {
		Long nonExistingId = 1L;
		when(adRepository.findOne(1L)).thenReturn(null);
		
		Ad nonExisting = adServiceImpl.findOne(nonExistingId);
		assertNull(nonExisting);
	}

	@Test
	public void testSaveAd() {
		Ad ad = new Ad();
		when(adRepository.save(ad)).thenReturn(ad);
		
		
		Ad result = adServiceImpl.save(ad);
		
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MONTH, 3);
		assertEquals(ad.getExpirationDate().getMonth(), c.getTime().getMonth());
		assertEquals(ad, result);
	}

	@Test
	public void testSaveAdDtoListOfLongString() throws IOException {
		//given
		String username = "user";
		User u = new User();
		u.setUsername(username);
		AdDto dto = new HouseAdDto();
		Ad converted = new Ad();
		House h = new House();
		h.setLocation(new Location("locationName"));
		converted.setRealEstate(h);
		List<Long> equipmentIds = Arrays.asList(1L, 2L, 3L);
		FileBucket bucket = new FileBucket();
		bucket.setFile(new MockMultipartFile("image.jpg", new byte[3]));
		dto.setFile(bucket);
		
		when(userService.findByUsername(username)).thenReturn(u);
		when(toEntity.convert(dto)).thenReturn(converted);
		when(equipmentService.findByIds(new ArrayList<>())).thenReturn(new HashSet<>());
		when(locationService.findByName(h.getLocation().getName()))
		.thenReturn(Optional.of(new Location("locationName")));
		when(adRepository.save(converted)).thenReturn(converted);
		
		//when
		Ad result = adServiceImpl.save(dto, equipmentIds, username);
		
		//then
		assertEquals(converted.getAdType(), result.getAdType());
		assertEquals(converted.getDateCreated(), result.getDateCreated());
		assertEquals(converted.getDescription(), result.getDescription());
		assertEquals(converted.getExpirationDate(), result.getExpirationDate());
		assertEquals(converted.getId(), result.getId());
		assertEquals(converted.getPrice(), result.getPrice());
		assertEquals(converted.getRealEstate(), result.getRealEstate());
		assertEquals(converted.getTitle(), result.getTitle());
		assertEquals(converted.getUser(), result.getUser());
	}

	@Test
	public void testDelete() {
		Ad ad = new Ad();	
		
		adServiceImpl.delete(ad);
		
		verify(adRepository, times(1)).delete(ad);
	}

	@Test
	public void testFindNewest() {
		List<Ad> newest = Arrays.asList(new Ad(), new Ad(), new Ad());
		
		when(adRepository.findTop3ByOrderByDateCreatedDesc()).thenReturn(newest);
		
		List<Ad> result = adServiceImpl.findNewest();
		assertNotNull(result);
		assertEquals(3, result.size());
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
