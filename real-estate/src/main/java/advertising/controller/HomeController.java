package advertising.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class HomeController {

	//TODO Fix authentication-checking bugs and find a better way to implement it	
	
	@GetMapping
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("index");
		boolean auth = authenticated();
		mav.addObject("auth", auth);
		return mav;
	}

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
