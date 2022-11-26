package home.four.you.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class House extends RealEstate{

	@Column(name = "floors_number", nullable = false)
	private int floorsNumber;

	public int getFloorsNumber() {
		return floorsNumber;
	}

	public void setFloorsNumber(int floorsNumber) {
		this.floorsNumber = floorsNumber;
	}

}
