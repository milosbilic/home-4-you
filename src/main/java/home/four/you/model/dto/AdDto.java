package home.four.you.model.dto;

import home.four.you.model.entity.Ad;
import home.four.you.validation.annotation.Image;
import home.four.you.model.entity.Price;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.text.DecimalFormat;
import java.util.Date;

public abstract class AdDto {

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
	//private RealEstateDto realEstate;
	private Ad.Type adType;
	
	@Image
	private FileBucket file;
	
	public AdDto() {
	}
	
	public Long getId() {
		return id;
	}

	public PriceDto getPrice() {
		return price;
	}

	public abstract RealEstateDto getRealEstate();

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

	public abstract void setRealEstate(RealEstateDto realEstate);

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

	public Ad.Type getAdType() {
		return adType;
	}

	public void setAdType(Ad.Type adType) {
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
				+ ", expirationDate=" + expirationDate + ", user=" + user + ", price=" + price + ", adType=" + adType;
	}
}
