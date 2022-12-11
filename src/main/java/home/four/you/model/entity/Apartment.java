package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity class for Apartment model.
 */
@Entity
@Table(name = "apartments")
@Getter
@Setter
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private int floor;
}
