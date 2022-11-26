package home.four.you.model.entity;

import home.four.you.model.HeatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class RealEstate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Location location;

	@NotNull
	private double area;

	@Column(name = "rooms_number")
	@NotNull
	private double roomsNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "real_estate_equipment", joinColumns = @JoinColumn(name = "real_estate_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"))
    private Set<Equipment> equipment;

	@Enumerated(EnumType.STRING)
	@Column(name = "heat_type") // TODO think about not allowing null values
	private HeatType heatType;

	private boolean booked;

	@Lob
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
