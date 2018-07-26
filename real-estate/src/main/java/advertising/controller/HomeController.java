package advertising.controller;

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
	  return "login";
    }
	
	@GetMapping("accessDenied")
	public String accessDenied() {
		return "access_denied";
	}
}
