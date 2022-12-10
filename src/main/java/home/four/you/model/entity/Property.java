package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Entity class for Property model.
 */
@Entity
@Table(name = "properties")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
public abstract class Property {

    /**
     * Enumeration for property's heat type.
     */
    public enum HeatType {
        /**
         * Property has electric heating.
         */
        ELECTRIC,
        /**
         * Property has gas heating.
         */
        GAS,
        /**
         * Property has wood heating.
         */
        WOOD
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @Column
    private double area;

    @Column(name = "rooms_number")
    private double roomsNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "real_estate_equipment",
            joinColumns = @JoinColumn(name = "real_estate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id", referencedColumnName = "id"))
    private Set<Equipment> equipment;

    @Enumerated(EnumType.STRING)
    @Column(name = "heat_type") // TODO think about not allowing null values
    private HeatType heatType;

    @Column
    private boolean booked;

    @Lob
    private byte[] image;
}
