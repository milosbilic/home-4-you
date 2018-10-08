package advertising.dto;

import advertising.enums.AdType;
import advertising.model.Apartment;
import advertising.model.House;
import advertising.model.RealEstate;

public class SearchDto {

	private String location;
	private Double minPrice;
	private Double maxPrice;
	private Double minArea;
	private Double maxArea;
	private String adType;
	private String realEstateType;

	private AdType adTypeEnum;
	private Class<?> realEstateClass;

	public SearchDto() {
	}

	public SearchDto(String location, Double minPrice, Double maxPrice, Double minArea, Double maxArea) {
		this.location = location;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minArea = minArea;
		this.maxArea = maxArea;
	}

	public String getLocation() {
		return location;
	}

	public Double getMaxArea() {
		return maxArea;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public Double getMinArea() {
		return minArea;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMaxArea(Double maxArea) {
		this.maxArea = maxArea;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinArea(Double minArea) {
		this.minArea = minArea;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public String getAdType() {
		return adType;
	}

	public void setAdType(String adType) {
		this.adType = adType;
	}

	public String getRealEstateType() {
		return realEstateType;
	}

	public void setRealEstateType(String realEstateType) {
		this.realEstateType = realEstateType;
	}

	public AdType getAdTypeEnum() {
		return adTypeEnum;
	}

	public void setAdTypeEnum(AdType adTypeEnum) {
		this.adTypeEnum = adTypeEnum;
	}

	public void setAdTypeEnum(String adType) {
		this.adTypeEnum = AdType.fromValue(adType);
	}

	public Class<?> getRealEstateClass() {
		return realEstateClass;
	}

	public void setRealEstateClass(Class<RealEstate> realEstateClass) {
		this.realEstateClass = realEstateClass;
	}

	public void setRealEstateClass(String className) {
		if (className.equalsIgnoreCase(House.class.getSimpleName())) {
			this.realEstateClass = House.class;
		} else if (className.equalsIgnoreCase(Apartment.class.getSimpleName())) {
			this.realEstateClass = Apartment.class;
		} else {
			throw new IllegalArgumentException("There is no such class!");
		}

	}

	@Override
	public String toString() {
		return "SearchDto [location=" + location + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", minArea="
				+ minArea + ", maxArea=" + maxArea + ", adType=" + adType + ", realEstateType=" + realEstateType
				+ ", adTypeEnum=" + adTypeEnum + ", realEstateClass=" + realEstateClass + "]";
	}
	
	
}
