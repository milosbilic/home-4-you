package home.four.you.mapper;

import home.four.you.model.dto.CreateUserResponseDto;
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
     * Maps user entity to {@link CreateUserResponseDto}.
     *
     * @param user User entity.
     * @return Mapped created response.
     */
    CreateUserResponseDto mapToCreatedUserResponseDto(User user);
}
