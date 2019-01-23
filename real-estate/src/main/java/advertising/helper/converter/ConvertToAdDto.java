package advertising.helper.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import advertising.dto.AdDto;
import advertising.model.Ad;

@Component
public class ConvertToAdDto implements Converter<Ad, AdDto>{

	@Autowired
	private ConvertToUserDto toUserDto;
	
	@Autowired
	private ConvertToRealEstateDto toRealEstateDto;
	
	@Override
	public AdDto convert(Ad ad) {
		AdDto dto = convertNoUser(ad);
		dto.setUser(toUserDto.convertMinimum(ad.getUser()));
		return dto;
	}
	
	public AdDto convertNoUser(Ad ad) {
		AdDto dto = new AdDto();
		dto.setId(ad.getId());
		dto.setTitle(ad.getTitle());
		dto.setDescription(ad.getDescription());
		dto.setDateCreated(ad.getDateCreated());
		dto.setExpirationDate(ad.getExpirationDate());
		dto.setFormattedPrice(ad.getPrice());
		dto.setRealEstate(toRealEstateDto.convert(ad.getRealEstate()));
		dto.setAdType(ad.getAdType());
		return dto;
	}

	public List<AdDto> convert(List<Ad> ads) {
		List<AdDto> dtos = new ArrayList<>();
		for (Ad ad : ads) {
			dtos.add(convert(ad));
		}
		return dtos;
	}

	public List<AdDto> convertNoUser(List<Ad> ads) {
		List<AdDto> dtos = new ArrayList<>();
		for (Ad ad : ads) {
			dtos.add(convertNoUser(ad));
		}
		return dtos;
	}
}
