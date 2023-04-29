package home.four.you.mapper;

import home.four.you.model.dto.CreateUserResponseDto;
import home.four.you.model.dto.UserBriefDetailsDto;
import home.four.you.model.dto.UserDetailsDto;
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

    /**
     * Maps user entity to {@link UserDetailsDto}.
     *
     * @param user User entity.
     * @return Mapped details DTO.
     */
    UserDetailsDto mapToDetailsDto(User user);

    /**
     * Maps user entity to {@link UserBriefDetailsDto}.
     * @param user User entity.
     * @return Mapped details DTO.
     */
    UserBriefDetailsDto mapToBriefDetailsDto(User user);
}
