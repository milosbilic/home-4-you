package home.four.you.helper.converter;

import java.util.List;

import home.four.you.model.entity.Ad;
import home.four.you.model.entity.Price;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.*;

import home.four.you.dto.AdDto;

@Component
public class ConvertToAdEntity implements Converter<AdDto, Ad> {

//	@Autowired
//	private ConvertToUserEntity toUser;
	
//	@Autowired
//	private ConvertToRealEstateEntity toRealEstateEntity;
	
	@Override
	public Ad convert(AdDto dto) {
		Ad ad = new Ad();
		ad.setAdType(dto.getAdType());
		ad.setTitle(dto.getTitle());
		ad.setDescription(dto.getDescription());
		ad.setDateCreated(dto.getDateCreated());
//		ad.setRealEstate(toRealEstateEntity.convert(dto.getRealEstate()));
		ad.setPrice(new Price(dto.getPrice().getAmount()));
		
//		if (dto.getUser() != null)
//			ad.setUser(toUser.convert(dto.getUser()));
		if (dto.getId() != null)
			ad.setId(dto.getId());
		if (dto.getExpirationDate() != null)
			ad.setExpirationDate(dto.getExpirationDate());
		return ad;
	}

	public List<Ad> convert(List<AdDto> dtos) {
		return dtos.stream().map(x -> convert(x)).collect(toList());
	}

}
