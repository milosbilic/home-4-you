package advertising.helper.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import advertising.dto.AdDto;
import advertising.dto.ApartmentAdDto;
import advertising.dto.HouseAdDto;
import advertising.model.Ad;
import advertising.model.Apartment;
import advertising.model.House;

@Component
public class ConvertToAdDto implements Converter<Ad, AdDto>{

	@Autowired
	private ConvertToUserDto toUserDto;
	
	@Autowired
	private ConvertToRealEstateDto toRealEstateDto;
	
	@Override
	public AdDto convert(Ad ad) {
		AdDto dto = convertWithoutUser(ad);
		dto.setUser(toUserDto.convertMinimum(ad.getUser()));
		return dto;
	}
	
	public AdDto convertWithoutUser(Ad ad) {
		AdDto dto = getInstance(ad);
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
		return ads.stream().map(x -> convert(x)).collect(Collectors.toList());
	}

	public List<AdDto> convertNoUser(List<Ad> ads) {
		return ads.stream().map(x -> convertWithoutUser(x)).collect(Collectors.toList());
	}
	
	private AdDto getInstance(Ad ad) {
		AdDto retVal = null;
		if (ad.getRealEstate() instanceof House)
			retVal = new HouseAdDto();
		if (ad.getRealEstate() instanceof Apartment) {
			retVal = new ApartmentAdDto();
		}
		return retVal;
	}
}
