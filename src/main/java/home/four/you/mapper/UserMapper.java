package home.four.you.mapper;

import home.four.you.model.dto.UserBriefDetailsDto;
import home.four.you.model.dto.UserDto;
import home.four.you.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for {@link User} entity.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * Maps {@link User} to {@link UserDto}.
     *
     * @param user User.
     * @return Mapped user.
     */
    UserDto mapToDto(User user);


    UserBriefDetailsDto mapToBriefDetails(User user);
}
