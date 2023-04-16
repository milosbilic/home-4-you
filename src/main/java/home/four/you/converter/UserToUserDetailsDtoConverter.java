package home.four.you.converter;

import home.four.you.mapper.UserMapper;
import home.four.you.model.dto.UserDetailsDto;
import home.four.you.model.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from {@link User} to {@link UserDetailsDto}.
 */
public class UserToUserDetailsDtoConverter implements Converter<User, UserDetailsDto> {

    @Override
    public UserDetailsDto convert(User source) {
        return UserMapper.INSTANCE.mapToDetailsDto(source);
    }
}
