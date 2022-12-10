package home.four.you.controller;

import home.four.you.model.dto.UserDto;
import home.four.you.model.entity.User;
import home.four.you.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

	private final UserService userService;
	
	private static final String HAS_ANY_ROLE = "hasAnyRole('USER', 'ADMIN')";
	
//	@GetMapping
//	public List<UserDto> getUsers() {
//		return toDto.convert(userService.findAll());
//	}
//	
//	@GetMapping("/{id}")
//	public UserDto findOne(@PathVariable Long id) {
//		return toDto.convert(userService.findOne(id));
//	}
	
	@PostMapping
	public ModelAndView createUser(
			@ModelAttribute("user") @Valid UserDto userDto, BindingResult result,
			WebRequest request, Errors errors) {
		log.debug("Creating a new user [{}]", userDto);
		User registered = null;
		if (!result.hasErrors())
			registered = userService.createUserAccount(userDto);
		if (registered == null)
			result.rejectValue("email", "message.regError");
		
		if (result.hasErrors()) {
	        return new ModelAndView("users/registration", "user", userDto);
	    }
		return new ModelAndView("redirect:/users/" + registered.getId());
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize(HAS_ANY_ROLE)
	public String editUser(@PathVariable Long id,
						   @ModelAttribute("userDto") @Valid UserDto userDto,
						   BindingResult bindingResult,
						   WebRequest request, 
						   Errors errors) {
//		userService.save(toEntity.convert(userDto));
		return "redirect:/users/{id}";
	}
	
//	@DeleteMapping("/{id}")
//	@PreAuthorize(HAS_ANY_ROLE)
//	public ResponseEntity<UserDto> delete(@PathVariable Long id) {
//		UserDto user = toDto.convert(userService.findOne(id));
//		userService.delete(userService.findOne(id));
//		return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
//	}
	
	@GetMapping("/registration")
	public ModelAndView register() {
		ModelAndView mav = new ModelAndView("users/registration");
		mav.addObject("user", new UserDto());
		return mav;
	}
	
}
