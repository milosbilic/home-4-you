package advertising.dto;

import java.util.Currency;

public class PriceDto {

	private Long id;
	private String amount;
	private Currency currency;

	public PriceDto() {
	}

	public PriceDto(Long id, Currency currency) {
		this.id = id;
		this.currency = currency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
