package home.four.you.controller;

import home.four.you.model.dto.AdDto;
import home.four.you.model.dto.HouseAdDto;
import home.four.you.model.dto.SearchDto;
import home.four.you.converter.AdToAdDtoConverter;
import home.four.you.model.entity.Ad;
import home.four.you.service.AdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class HomeControllerTest {

	@Mock
	AdService adService;
	
	@Mock
	AdToAdDtoConverter toDto;
	
	@InjectMocks
	HomeController homeController;
	
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
	}

	@Test
	public void testIndex() throws Exception {
		
		List<Ad> ads = Arrays.asList(new Ad(), new Ad(), new Ad());
		List<AdDto> dtos = Arrays.asList(new HouseAdDto(), new HouseAdDto(), new HouseAdDto());

		when(adService.findNewest()).thenReturn(ads);
//		when(toDto.convert(ads)).thenReturn(dtos);
		
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
