package advertising.dto;

import java.util.Date;

import advertising.model.Price;

public class AdDto {

	private Long id;

	private String title;

	private String description;

	private Date dateCreated;

	private Date expirationDate;
	private UserDto user;
	
	private Price price;
	private RealEstateDto realEstate;
	
	
	public Date getDateCreated() {
		return dateCreated;
	}
	public String getDescription() {
		return description;
	}
	
	public Date getExpirationDate() {
		return expirationDate;
	}

	public Long getId() {
		return id;
	}

	public Price getPrice() {
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

	public void setPrice(Price price) {
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
}
