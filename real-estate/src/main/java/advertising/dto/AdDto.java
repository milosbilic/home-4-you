package advertising.dto;

import java.text.DecimalFormat;
import java.util.Date;

import advertising.model.Price;

public class AdDto {

	private Long id;

	private String title;

	private String description;

	private Date dateCreated;

	private Date expirationDate;
	private UserDto user;
	
	private PriceDto price;
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

	public void setPrice(Price price) {
		String pattern = "###,###.00";
		DecimalFormat format = new DecimalFormat(pattern);
		this.price = new PriceDto(price.getId(), price.getCurrency());
		//setting amount String value from a BigDecimal
		this.price.setAmount(format.format(price.getAmount()));
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
