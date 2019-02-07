package advertising.controller;

import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;

import advertising.dto.UserDto;
import advertising.helper.converter.ConvertToUserEntity;
import advertising.model.User;
import advertising.service.UserService;

public class UserControllerTest {

	@Mock
	UserService userService;
	
	@Mock
	ConvertToUserEntity toEntity;
	
	@InjectMocks
	UserController userController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();		
	}
	
	@Test
	public void whenPassingInvalidUser_creationShouldFail() throws Exception {
		mockMvc.perform(post(("/users"))
				.param("username", "us")
				.param("password", "pass")
				.param("matchingPassword", "pas")
				.param("firstName", "f")
				.param("lastName", "a")
				.param("email", "email")
				.param("phone", ""))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(model().attributeHasFieldErrors("user", "username"))
//		.andExpect(model().attributeHasFieldErrors("user", "password"))
//		.andExpect(model().attributeHasFieldErrors("user", "matchingPassword"))
		.andExpect(model().attributeHasFieldErrors("user", "firstName"))
		.andExpect(model().attributeHasFieldErrors("user", "lastName"))
		.andExpect(model().attributeHasFieldErrors("user", "email"))
		.andExpect(model().attributeHasFieldErrors("user", "phone"));
	}

	@Test
	public void testCreateUser_shouldSucceed() throws Exception {
		User u = new User();
		when(userService.createUserAccount(Matchers.<UserDto>any())).thenReturn(u);
		
		mockMvc.perform(post(("/users"))
				.param("username", "username")
				.param("password", "password")
				.param("matchingPassword", "password")
				.param("firstName", "First")
				.param("lastName", "Last")
				.param("email", "test@gmail.com")
				.param("phone", "+3815390923092"))
		.andDo(print())
		.andExpect(status().isFound())
		.andExpect(redirectedUrl("/users/" + u.getId()));
	}

	@Test
	public void testRegister() throws Exception {
		mockMvc.perform(get("/users/registration"))
		.andExpect(status().isOk())
		.andExpect(model().attribute("user", notNullValue()))
		.andExpect(model().attribute("user", instanceOf(UserDto.class)))
		.andExpect(view().name("users/registration"));
	}

}
