package advertising.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import advertising.dto.AdDto;
import advertising.dto.HouseAdDto;
import advertising.dto.SearchDto;
import advertising.helper.converter.ConvertToAdDto;
import advertising.model.Ad;
import advertising.service.AdService;

public class HomeControllerTest {

	@Mock
	AdService adService;
	
	@Mock
	ConvertToAdDto toDto;
	
	@InjectMocks
	HomeController homeController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void testIndex() throws Exception {
		
		List<Ad> ads = Arrays.asList(new Ad(), new Ad(), new Ad());
		List<AdDto> dtos = Arrays.asList(new HouseAdDto(), new HouseAdDto(), new HouseAdDto());

		when(adService.findNewest()).thenReturn(ads);
		when(toDto.convert(ads)).thenReturn(dtos);
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("newestAds", hasSize(3)))
		.andExpect(model().attribute("realEstates", notNullValue()))
		.andExpect(model().attribute("adTypes", notNullValue()))
		.andExpect(model().attribute("search", instanceOf(SearchDto.class)))
		.andExpect(view().name("index"));
	}

//	@Test
//	public void testLogin() throws Exception {
//		mockMvc.perform(get("/login"))
//		.andExpect(status().isOk())
//		.andExpect(view().name("login"));
//	}

	@Test
	public void testAccessDenied() throws Exception {
		mockMvc.perform(get("/accessDenied"))
		.andExpect(status().isOk())
		.andExpect(view().name("access_denied"));
	}

}
