package advertising.dto;

import javax.validation.constraints.Min;

import advertising.enums.AdType;
import advertising.enums.RealEstateType;

public class SearchDto {

	private static final int MAX_VALUE = 999999999;
	private String location;

	@Min(0)
	private int minPrice;
	private int maxPrice;

	@Min(0)
	private int minArea;
	private int maxArea;
	
	private String adType;
	private String realEstateType;

	public SearchDto() {
		this.maxPrice = MAX_VALUE;
		this.maxArea = MAX_VALUE;
	}

	public SearchDto(String location, int minPrice, int maxPrice, int minArea, int maxArea) {
		this.location = location;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.minArea = minArea;
		this.maxArea = maxArea;
	}

	public String getLocation() {
		return location;
	}

	public int getMaxArea() {
		return maxArea;
	}

	public int getMaxPrice() {
		return maxPrice;
	}

	public int getMinArea() {
		return minArea;
	}

	public int getMinPrice() {
		return minPrice;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setMaxArea(int maxArea) {
		this.maxArea = maxArea;
	}

	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}

	public void setMinArea(int minArea) {
		this.minArea = minArea;
	}

	public void setMinPrice(int minPrice) {
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

	@Override
	public String toString() {
		return "SearchDto [location=" + location + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", minArea="
				+ minArea + ", maxArea=" + maxArea + ", adType=" + adType + ", realEstateType=" + realEstateType + "]";
	}
}
