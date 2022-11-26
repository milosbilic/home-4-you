package home.four.you.controller;

import home.four.you.helper.converter.ConvertToAdDto;
import home.four.you.service.SearchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class SearchControllerTest {

    @Mock
    SearchService searchService;

    @Mock
    ConvertToAdDto toAdDto;

    @InjectMocks
    SearchController searchController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchController).build();
    }

    @Test
    public void whenSearchWithInvalidPriceAndArea_shouldReturnIndexView() throws Exception {
        String invalidMinPrice = "-Invalid";
        String invalidMinArea = invalidMinPrice;

        mockMvc.perform(post("/search")
                        .param("minPrice", invalidMinPrice)
                        .param("minArea", invalidMinArea))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeHasFieldErrors("search", "minPrice"))
                .andExpect(model().attributeHasFieldErrors("search", "minArea"));
    }

    @Test
    public void testSearchWithValidParameters() throws Exception {
        String minPrice = "0";
        String maxPrice = "30000";
        String minArea = "0";
        String maxArea = "30000";

        mockMvc.perform(post("/search")
                        .param("minPrice", minPrice)
                        .param("minArea", minArea)
                        .param("maxPrice", maxPrice)
                        .param("maxArea", maxArea))
                .andDo(print())
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/search/show"));
    }

/*	@Test
	public void testPagination() throws Exception {
		//given
		Page<Ad> ads = new PageImpl<>(Arrays.asList(new Ad(), new Ad(), new Ad()));
		List<AdDto> dtos = new ArrayList<>(Arrays.asList(new HouseAdDto(), new HouseAdDto()));
		Map<String, Object> sessionAttr = new HashMap<>();
		sessionAttr.put("searchCriteria", new SearchDto());
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("searchCriteria", new SearchDto());
		
		
		//when
		when(searchService.search(new SearchDto(), new PageRequest(0, 10)))
		.thenReturn(ads);
		when(toAdDto.convertNoUser(ads.getContent())).thenReturn(dtos);
		
		//then
		mockMvc.perform(get("/search/show")
				.session(session))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attribute("page", hasSize(2)))
		.andExpect(view().name("/ads/search_results"));
	}
*/
}
