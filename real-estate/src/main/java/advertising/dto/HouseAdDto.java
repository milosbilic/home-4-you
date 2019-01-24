package advertising.dto;

public class HouseAdDto extends AdDto {
	
	private HouseDto realEstate;

	@Override
	public HouseDto getRealEstate() {
		return realEstate;
	}

	@Override
	public void setRealEstate(RealEstateDto realEstate) {
		this.realEstate = (HouseDto) realEstate;
	}

}
