package advertising.model;

import javax.persistence.Column;
import javax.persistence.Entity;

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
