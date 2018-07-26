package advertising.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Appartment extends RealEstate {

	@Column(nullable = false)
	private int floor;

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}
	
	
	
}
