package home.four.you.controller;

import home.four.you.model.dto.SearchDto;
import home.four.you.model.entity.Ad;
import home.four.you.service.LocationService;
import home.four.you.service.SearchService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/search")
@SessionAttributes("searchCritirea")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
//	@Autowired
//	private ConvertToAdDto toAdDto;
	
	@Autowired
	private LocationService locationService;

	@PostMapping
	public String search(@ModelAttribute("search") @Valid SearchDto searchDto,
			BindingResult bindingResult, HttpSession httpSession)throws BindException {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		httpSession.setAttribute("searchCritirea", searchDto);
		return "redirect:/search/show";
	}
	
	@GetMapping("/show")
	public ModelAndView pagination(@ModelAttribute("searchCritirea") SearchDto searchDto,
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		ModelAndView mav = new ModelAndView("/ads/search_results");
		Page<Ad> ads = searchService.search(searchDto, pageable);
//		Page<AdDto> page = new PageImpl<>(toAdDto.convertNoUser(ads.getContent()),
//				pageable, ads.getTotalElements());
//		mav.addObject("page", page);

		return mav;
	}
}

