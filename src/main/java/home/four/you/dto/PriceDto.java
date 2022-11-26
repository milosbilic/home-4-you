package home.four.you.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

public class PriceDto {

	private Long id;
	
	@Min(0)
	@NotNull
	private BigDecimal amount;
	private String amountOutput;
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

	public String getAmountOutput() {
		return amountOutput;
	}

	public void setAmountOutput(String amount) {
		this.amountOutput = amount;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
