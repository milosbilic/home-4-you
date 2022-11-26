package home.four.you.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Apartment extends RealEstate {

	@Column(nullable = false)
	private int floor;

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}



}
