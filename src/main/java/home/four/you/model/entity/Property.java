package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Property model.
 */
@Entity
@Table(name = "properties")
@Getter
@Setter
public class Property {

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

    @Column(name = "number_of_rooms")
    private double numberOfRooms;

    @Enumerated(EnumType.STRING)
    @Column(name = "heat_type")
    private HeatType heatType;

    @Column
    private boolean booked;

    @Lob
    private byte[] image;

    @ManyToMany(mappedBy = "properties", fetch = FetchType.LAZY)
    private Set<Equipment> equipment = new HashSet<>();

    @OneToOne(mappedBy = "property")
    private House house;

    @OneToOne(mappedBy = "property")
    private Apartment apartment;
}
