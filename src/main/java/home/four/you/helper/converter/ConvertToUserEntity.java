package home.four.you.helper.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import home.four.you.dto.UserDto;
import home.four.you.model.entity.User;

@Component
public class ConvertToUserEntity implements Converter<UserDto, User> {

	@Autowired
	private ConvertToAdEntity toAd;
	
	@Override
	public User convert(UserDto dto) {
		User user = convertMinimum(dto);
		user.setAds(toAd.convert(dto.getAds()));
		return user;
	}

	public User convertMinimum(UserDto dto) {
		User user = new User();
		user.setId(dto.getId());
		user.setUsername(dto.getUsername());
		user.setPassword(dto.getPassword());
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());
		return user;
	}

}
