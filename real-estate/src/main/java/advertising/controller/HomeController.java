package advertising.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/")
public class HomeController {	
	
	@GetMapping
	public String index() {
		return "index";
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
