package home.four.you.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for House model.
 */
@Entity
@Table(name = "houses")
@Getter
@Setter
public class House extends Property {

	@Column(name = "number_of_floors", nullable = false)
	private int numberOfFloors;
}
