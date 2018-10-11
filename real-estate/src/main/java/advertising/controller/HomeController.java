package advertising.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import advertising.dto.AdDto;
import advertising.dto.SearchDto;
import advertising.enums.AdType;
import advertising.enums.RealEstateType;
import advertising.helper.converter.ConvertToAdDto;
import advertising.service.AdService;

@Controller
@RequestMapping(value = "/")
public class HomeController {	
	
	@Autowired
	private AdService adService;
	
	@Autowired
	private ConvertToAdDto toDto;
	
	@GetMapping
	public ModelAndView index(HttpSession httpSession) {
		httpSession.removeAttribute("searchCritirea");
		ModelAndView mav = new ModelAndView("index");
		mav.addObject("newestAds", toDto.convert(adService.findNewest()));
		mav.addObject("adTypes", AdType.values());
		mav.addObject("realEstates", RealEstateType.values());
		mav.addObject("search", new SearchDto());
		return mav;
	}
	
/*	@GetMapping("/tet")
	@ResponseBody
	public List<AdDto> tet() {
		return toDto.convert(adService.findNewest());
	} */

	@GetMapping("login")
	public String login() {
		if (authenticated())
			return "index";

		return "login";
	}

	@GetMapping("accessDenied")
	public String accessDenied() {
		return "access_denied";
	}

	private boolean authenticated() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return true;
		}
		return false;
	}
}
