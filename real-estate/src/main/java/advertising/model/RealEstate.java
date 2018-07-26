package advertising.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import advertising.enums.HeatType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RealEstate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Location location;
	
	@NotNull
	private double area;
	
	@Column(name = "rooms_number")
	@NotNull
	private double roomsNumber;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "real_estate_equipment", 
        joinColumns = @JoinColumn(
          name = "real_estate_id", referencedColumnName = "id"), 
        inverseJoinColumns = @JoinColumn(
          name = "equipment_id", referencedColumnName = "id"))
	private Set<Equipment> equipment;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "heat_type")
	private HeatType heatType;
	
	private boolean booked;

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

	public Set<Equipment> getEquipment() {
		return equipment;
	}

	public void setEquipment(Set<Equipment> equipment) {
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
	
}
