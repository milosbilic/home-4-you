package advertising.dto;

import java.text.DecimalFormat;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import advertising.enums.AdType;
import advertising.enums.RealEstateType;
import advertising.helper.validation.annotation.Image;
import advertising.model.Price;

public class AdDto {

	private Long id;
	
	@NotNull
	private String title;
	private String description;
	private Date dateCreated;
	private Date expirationDate;
	private UserDto user;

	@Valid
	private PriceDto price;
	
	//@Valid
	private RealEstateDto realEstate;
	private AdType adType;
	
	@Image
	private FileBucket file;
	
	public AdDto() {
	}
	

	public AdDto(RealEstateType realEstateType) {
		switch(realEstateType) {
		case HOUSE:
			realEstate = new HouseDto();
			break;
		case APARTMENT:
			realEstate = new ApartmentDto();
			break;
			default:
				throw new RuntimeException();
		}
	}

	public Long getId() {
		return id;
	}

	public PriceDto getPrice() {
		return price;
	}

	public RealEstateDto getRealEstate() {
		return realEstate;
	}

	public String getTitle() {
		return title;
	}

	public UserDto getUser() {
		return user;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setFormattedPrice(Price price) {
		String pattern = "###,###.00";
		DecimalFormat format = new DecimalFormat(pattern);
		this.price = new PriceDto(price.getId(), price.getCurrency());
		// setting amount String value from a BigDecimal
		this.price.setAmountOutput(format.format(price.getAmount()));
	}
	
	public void setPrice(PriceDto price) {
		this.price = price;
	}

	public void setRealEstate(RealEstateDto realEstate) {
		this.realEstate = realEstate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getDescription() {
		return description;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public AdType getAdType() {
		return adType;
	}

	public void setAdType(AdType adType) {
		this.adType = adType;
	}

	public FileBucket getFile() {
		return file;
	}

	public void setFile(FileBucket file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "AdDto [id=" + id + ", title=" + title + ", description=" + description + ", dateCreated=" + dateCreated
				+ ", expirationDate=" + expirationDate + ", user=" + user + ", price=" + price + ", realEstate="
				+ realEstate + ", adType=" + adType;
	}
}
