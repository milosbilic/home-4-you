package home.four.you.mapper;

import home.four.you.model.dto.UserBriefDetailsDto;
import home.four.you.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapper for {@link User} entity.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);



    UserBriefDetailsDto mapToBriefDetails(User user);
}
