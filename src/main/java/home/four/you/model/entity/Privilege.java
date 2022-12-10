package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * Entity class for Privilege model.
 */
@Entity
@Table(name = "privileges")
@Getter
@Setter
public class Privilege {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String name;

	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;
}
