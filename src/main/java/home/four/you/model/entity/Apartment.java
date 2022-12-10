package home.four.you.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Apartment model.
 */
@Entity
@Table(name = "apartments")
@Getter
@Setter
public class Apartment extends Property {

	@Column(nullable = false)
	private int floor;
}
