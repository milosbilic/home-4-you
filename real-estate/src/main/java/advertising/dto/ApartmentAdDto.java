package advertising.dto;

public class ApartmentAdDto extends AdDto {
	
	private ApartmentDto realEstate;

	@Override
	public ApartmentDto getRealEstate() {
		return realEstate;
	}

	@Override
	public void setRealEstate(RealEstateDto realEstate) {
		this.realEstate = (ApartmentDto) realEstate;
	}

}
