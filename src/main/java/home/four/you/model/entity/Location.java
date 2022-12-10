package home.four.you.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class for Location model.
 */
@Entity
@Getter
@Setter
@Table(name = "locations")
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
	private Set<Property> properties;

	@Override
	public String toString() {
		return "Location [id=" + id + ", name=" + name + ", zipCode=" + zipCode + ", realEstates=" + properties + "]";
	}

}
