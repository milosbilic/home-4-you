package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Entity class for Apartment model.
 */
@Entity
@Table(name = "apartments")
@Getter
@Setter
@Accessors(chain = true)
public class Apartment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Integer floor;

	@OneToOne
	@JoinColumn(name = "property_id")
	private Property property;
}
