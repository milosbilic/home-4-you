package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Entity class for Location model.
 */
@Entity
@Getter
@Setter
@Table(name = "locations")
@Accessors(chain = true)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "zip_code", unique = true)
    private String zipCode;
}
