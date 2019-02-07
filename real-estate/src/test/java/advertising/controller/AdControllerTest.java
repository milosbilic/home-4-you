package advertising.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasValue;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.apache.tomcat.jni.File;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.NotNull;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import advertising.dto.FileBucket;
import advertising.dto.HouseAdDto;
import advertising.enums.RealEstateType;
import advertising.helper.converter.ConvertToAdDto;
import advertising.helper.converter.ConvertToEquipmentDto;
import advertising.model.Ad;
import advertising.service.AdService;
import advertising.service.ApartmentService;
import advertising.service.EquipmentService;
import advertising.service.HouseService;

public class AdControllerTest {

	@Mock
	AdService adService;

	@Mock
	HouseService houseService;

	@Mock
	ApartmentService apartmentService;

	@Mock
	EquipmentService equipmentService;

	@Mock
	ConvertToAdDto toDto;

	@Mock
	ConvertToEquipmentDto toEquipmentDto;
	
	@InjectMocks
	AdController adController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(adController).build();
	}

	@Test
	public void testFindAll() {
		//not implemented
	}

	@Test
	public void testFindOne() throws Exception {
		Ad ad = new Ad();
		
		when(adService.findOne(1L)).thenReturn(ad);
		when(toDto.convert(ad)).thenReturn(new HouseAdDto());
		
		mockMvc.perform(get("/ads/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(view().name("ads/show"))
		.andExpect(model().attribute("ad", notNullValue()));
	}
	
	@Test
	public void whenInvalidParameterInputFindingAd_thenReturnClientError() throws Exception {
		Ad ad = new Ad();
		
		when(adService.findOne(1L)).thenReturn(ad);
		when(toDto.convert(ad)).thenReturn(new HouseAdDto());
		
		mockMvc.perform(get("/ads/invalid"))//invalid path variable format
		.andDo(print())
		.andExpect(status().isBadRequest());
	}



	@Test
	public void testNewAdd() throws Exception {
		mockMvc.perform(get("/ads/new").param("realEstateType", "house"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attribute("adTypes", notNullValue()))
		.andExpect(model().attribute("heatTypes", notNullValue()))
		.andExpect(model().attribute("newAd", notNullValue()))
		.andExpect(model().attribute("realEstateType", notNullValue()))
		.andExpect(view().name("ads/new"));
		
	}

	
	@Test
	public void testDelete() {
		//not implemented
	}

}
