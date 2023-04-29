package home.four.you.converter;

import home.four.you.mapper.UserMapper;
import home.four.you.model.dto.UserBriefDetailsDto;
import home.four.you.model.entity.User;
import org.springframework.core.convert.converter.Converter;

/**
 * Converter from {@link User} to {@link UserBriefDetailsDto}.
 */
public class UserToUserBriefDetailsDtoConverter implements Converter<User, UserBriefDetailsDto> {

    @Override
    public UserBriefDetailsDto convert(User source) {
        return UserMapper.INSTANCE.mapToBriefDetailsDto(source);
    }
}
