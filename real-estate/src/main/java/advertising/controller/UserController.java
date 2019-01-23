package advertising.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import advertising.dto.UserDto;
import advertising.helper.converter.ConvertToUserDto;
import advertising.helper.converter.ConvertToUserEntity;
import advertising.model.User;
import advertising.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private ConvertToUserDto toDto;
	
	@Autowired
	private ConvertToUserEntity toEntity;
	
	private static final String HAS_ANY_ROLE = "hasAnyRole('USER', 'ADMIN')";
	
	@GetMapping
	public List<UserDto> getUsers() {
		return toDto.convert(userService.findAll());
	}
	
	@GetMapping("/{id}")
	public UserDto findOne(@PathVariable Long id) {
		return toDto.convert(userService.findOne(id));
	}
	
	@PostMapping()
	public ModelAndView createUser(
			@ModelAttribute("user") @Valid UserDto userDto, BindingResult result,
			WebRequest request, Errors errors) {
		User registered = null;
		if (!result.hasErrors())
			registered = userService.createUserAccount(userDto, result);
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
		userService.save(toEntity.convert(userDto));
		return "redirect:/users/{id}";
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize(HAS_ANY_ROLE)
	public ResponseEntity<UserDto> delete(@PathVariable Long id) {
		UserDto user = toDto.convert(userService.findOne(id));
		userService.delete(userService.findOne(id));
		return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/registration")
	public ModelAndView register(WebRequest request, Model model) {
		ModelAndView mav = new ModelAndView("users/registration");
		mav.addObject("user", new UserDto());
		return mav;
	}
	
}
