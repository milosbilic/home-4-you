package home.four.you.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Equipment model.
 */
@Entity
@Table(name = "equipment")
@Getter
@Setter
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @ManyToMany
    @JoinTable(name = "property_equipment",
            joinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"))
    private Set<Property> properties = new HashSet<>();
}
