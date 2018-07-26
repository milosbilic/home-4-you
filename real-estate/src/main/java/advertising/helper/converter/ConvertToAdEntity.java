package advertising.helper.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import advertising.dto.AdDto;
import advertising.model.Ad;

@Component
public class ConvertToAdEntity implements Converter<AdDto, Ad> {

	@Autowired
	private ConvertToUserEntity toUser;
	
	@Override
	public Ad convert(AdDto dto) {
		Ad ad = new Ad();
		ad.setId(dto.getId());
		ad.setTitle(dto.getTitle());
		ad.setDescription(dto.getDescription());
		ad.setDateCreated(dto.getDateCreated());
		ad.setExpirationDate(dto.getExpirationDate());
		ad.setUser(toUser.convert(dto.getUser()));
	//	ad.setCategory(toCategory.convert(dto.getCategory()));
		return ad;
	}

	public List<Ad> convert(List<AdDto> dtos) {
		List<Ad> ads = new ArrayList<>();
		for (AdDto dto : dtos) {
			ads.add(convert(dto));
		}
		return ads;
	}

}
