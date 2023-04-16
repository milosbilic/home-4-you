package home.four.you.controller;

import home.four.you.model.dto.CreateUserRequestDto;
import home.four.you.model.dto.CreateUserResponseDto;
import home.four.you.model.dto.UserDetailsDto;
import home.four.you.model.entity.User;
import home.four.you.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;

import static home.four.you.TestUtil.generateId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link UserController}.
 */
@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    ConversionService conversionService;

    UserController controller;

    @BeforeEach
    void setUp() {
        controller = new UserController(userService, conversionService);
    }

    @Test
    @DisplayName("Create user - ok")
    void createUser_ok() {
        var dto = mock(CreateUserRequestDto.class);
        var user = mock(User.class);
        var responseDto = mock(CreateUserResponseDto.class);

        when(userService.createUser(dto)).thenReturn(user);
        when(conversionService.convert(user, CreateUserResponseDto.class)).thenReturn(responseDto);

        var result = controller.createUser(dto);

        assertThat(result).isEqualTo(responseDto);
    }

    @Test
    @DisplayName("Get details - ok")
    void getDetails_ok() {
        Long id = generateId();
        var user = mock(User.class);
        var dto = mock(UserDetailsDto.class);

        when(userService.findById(id)).thenReturn(user);
        when(conversionService.convert(user, UserDetailsDto.class)).thenReturn(dto);

        var result = controller.details(id);

        assertThat(result).isEqualTo(dto);
    }
}