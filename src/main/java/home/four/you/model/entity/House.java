package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for House model.
 */
@Entity
@Table(name = "houses")
@Getter
@Setter
public class House {

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "number_of_floors")
	private int numberOfFloors;

	@Column(name = "court_yard_area")
	private int courtYardArea;

	@OneToOne
	@JoinColumn(name = "property_id")
	private Property property;
}
