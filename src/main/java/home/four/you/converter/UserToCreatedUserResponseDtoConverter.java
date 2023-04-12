package home.four.you.converter;

import home.four.you.mapper.UserMapper;
import home.four.you.model.dto.CreateUserResponseDto;
import home.four.you.model.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from {@link User} to {@link CreateUserResponseDto}.
 */
public class UserToCreatedUserResponseDtoConverter implements Converter<User, CreateUserResponseDto> {

    @Override
    public CreateUserResponseDto convert(User source) {
        return UserMapper.INSTANCE.mapToCreatedUserResponseDto(source);
    }
}
