package home.four.you.model.dto;

import home.four.you.model.entity.Location;
import home.four.you.model.entity.Property.HeatType;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public abstract class RealEstateDto {
	
	private Long id;
	
	@NotNull
	private Location location;
	
	@NotNull
	private double area;
	
	@NotNull
	private double roomsNumber;
	
	private Set<EquipmentDto> equipment;
	
	private HeatType heatType;
	
	private boolean booked;
	
	private byte[] image;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public double getRoomsNumber() {
		return roomsNumber;
	}

	public void setRoomsNumber(double roomsNumber) {
		this.roomsNumber = roomsNumber;
	}

	public Set<EquipmentDto> getEquipment() {
		return equipment;
	}

	public void setEquipment(Set<EquipmentDto> equipment) {
		this.equipment = equipment;
	}

	public HeatType getHeatType() {
		return heatType;
	}

	public void setHeatType(HeatType heatType) {
		this.heatType = heatType;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "RealEstateDto [id=" + id + ", location=" + location + ", area=" + area + ", roomsNumber=" + roomsNumber
				+ ", equipment=" + equipment + ", heatType=" + heatType + ", booked=" + booked;
	}
	
	
	
}
