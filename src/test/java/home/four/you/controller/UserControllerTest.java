package home.four.you.controller;

import home.four.you.exception.ResourceNotFoundException;
import home.four.you.model.dto.CreateUserRequestDto;
import home.four.you.model.dto.CreateUserResponseDto;
import home.four.you.model.dto.UserBriefDetailsDto;
import home.four.you.model.dto.UserDetailsDto;
import home.four.you.model.entity.User;
import home.four.you.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static home.four.you.TestUtil.generateId;
import static home.four.you.exception.ErrorCode.NOT_FOUND;
import static home.four.you.exception.ErrorMessage.RESOURCE_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

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

        when(userService.findById(id)).thenReturn(Optional.of(user));
        when(conversionService.convert(user, UserDetailsDto.class)).thenReturn(dto);

        var result = controller.details(id);

        assertThat(result).isEqualTo(dto);
    }

    @Test
    @DisplayName("Get details - not found")
    void getDetails_notFound() {
        Long id = generateId();

        when(userService.findById(id)).thenReturn(Optional.empty());

        var ex = assertThrows(ResourceNotFoundException.class, () -> controller.details(id));

        assertAll(
                () -> assertThat(ex.getCode()).isEqualTo(NOT_FOUND),
                () -> assertThat(ex.getMessage()).isEqualTo(RESOURCE_NOT_FOUND)
        );
    }

    @Test
    @DisplayName("Delete user - ok")
    void deleteUser_ok() {
        Long id = generateId();

        controller.deleteUser(id);

        verify(userService, times(1)).delete(id);
    }

    @Test
    @DisplayName("Get all users - ok")
    void getAllUsers_ok() {
        var pageable = mock(Pageable.class);
        var user = mock(User.class);
        PageImpl<User> users = new PageImpl<>(List.of(user));
        var dto = mock(UserBriefDetailsDto.class);

        when(userService.findAll(pageable)).thenReturn(users);
        when(conversionService.convert(user, UserBriefDetailsDto.class)).thenReturn(dto);

        var result = controller.getAllUsers(pageable);

        assertThat(result).isEqualTo(new PageImpl<>(List.of(dto)));
    }
}
