package advertising.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import advertising.dto.AdDto;
import advertising.dto.SearchDto;
import advertising.enums.AdType;
import advertising.enums.RealEstateType;
import advertising.helper.converter.ConvertToAdDto;
import advertising.helper.converter.enums.AdTypeConverter;
import advertising.helper.converter.enums.RealEstateTypeConverter;
import advertising.model.Ad;
import advertising.model.Equipment;
import advertising.model.Location;
import advertising.service.LocationService;
import advertising.service.SearchService;

@Controller
@RequestMapping(value = "/search")
@SessionAttributes("searchCritirea")
public class SearchController {

	@Autowired
	private SearchService searchService;
	
	@Autowired
	private ConvertToAdDto toAdDto;
	
	@Autowired
	private LocationService locationService;
	
	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(AdType.class, new AdTypeConverter());
		webdataBinder.registerCustomEditor(RealEstateType.class, new RealEstateTypeConverter());
	}

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
		Page<AdDto> page = new PageImpl<>(toAdDto.convertNoUser(ads.getContent()),
				pageable, ads.getTotalElements());
		mav.addObject("page", page);

		return mav;
	}
}

