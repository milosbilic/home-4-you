package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Entity class for House model.
 */
@Entity
@Table(name = "houses")
@Getter
@Setter
@Accessors(chain = true)
public class House {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number_of_floors")
    private Integer numberOfFloors;

    @Column(name = "court_yard_area")
    private Integer courtYardArea;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;
}
