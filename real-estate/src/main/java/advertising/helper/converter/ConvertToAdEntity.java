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
	
	@Autowired
	private ConvertToRealEstateEntity toRealEstateEntity;
	
	@Override
	public Ad convert(AdDto dto) {
		Ad ad = new Ad();
		ad.setTitle(dto.getTitle());
		ad.setDescription(dto.getDescription());
		ad.setDateCreated(dto.getDateCreated());
		ad.setRealEstate(toRealEstateEntity.convert(dto.getRealEstate()));
		
		if (dto.getUser() != null)
			ad.setUser(toUser.convert(dto.getUser()));
		if (dto.getId() != null)
			ad.setId(dto.getId());
		if (dto.getExpirationDate() != null)
			ad.setExpirationDate(dto.getExpirationDate());
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
