package home.four.you.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String name;

	@Column(name = "zip_code", unique = true)
	//leave it nullable for now
	private String zipCode;

	@OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<RealEstate> realEstates;

	public Location() {
	}

	public Location(String name, String zipCode) {
		super();
		this.name = name;
		this.zipCode = zipCode;
	}

	public Location(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", zipCode=" + zipCode + ", realEstates=" + realEstates + "]";
	}

}
