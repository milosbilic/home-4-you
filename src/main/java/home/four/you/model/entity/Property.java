package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for Property model.
 */
@Entity
@Table(name = "properties")
@Getter
@Setter
@Accessors(chain = true)
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
        WOOD;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Location location;

    @Column
    private Integer area;

    @Column(name = "number_of_rooms")
    private Double numberOfRooms;

    @Enumerated(EnumType.STRING)
    @Column(name = "heat_type")
    private HeatType heatType;

    @Column
    private Boolean booked;

    @Lob
    private byte[] image;

    @ElementCollection(targetClass = Equipment.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "property_equipment")
    @Column(name = "equipment")
    private Set<Equipment> equipment = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "ad_id")
    private Ad ad;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private House house;

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private Apartment apartment;

    public Property setHouse(House house) {
        this.house = house;
        house.setProperty(this);

        return this;
    }

    public Property setApartment(Apartment apartment) {
        this.apartment = apartment;
        apartment.setProperty(this);

        return this;
    }
}
