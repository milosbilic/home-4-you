package home.four.you.converter;

import home.four.you.mapper.UserMapper;
import home.four.you.model.dto.UserDto;
import home.four.you.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertToUserDto implements Converter<User, UserDto> {

    @Autowired
    private AdToAdDtoConverter toAdDto;

    @Override
    public UserDto convert(User user) {
        return UserMapper.INSTANCE.mapToDto(user);
    }

    UserDto convertMinimum(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        return dto;
    }

    public List<UserDto> convert(List<User> users) {
        List<UserDto> dtos = new ArrayList<>();
        for (User u : users) {
            dtos.add(convertMinimum(u));
        }
        return dtos;
    }


}
