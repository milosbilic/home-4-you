package home.four.you.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Entity class for Ad model.
 */
@Entity
@Table(name = "ads")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Ad {

    /**
     * Enumeration for ad type.
     */
    public enum Type {

        /**
         * Property in the ad is for sale.
         */
        SALE,
        /**
         * Property in the ad is for rent.
         */
        RENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(nullable = false)
    private String title;

    @Column
    private String description;

    @Column(name = "date_created")
    @CreatedDate
    private LocalDate dateCreated;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.DATE)
    private LocalDate expirationDate;

    @Column
    private BigDecimal price;

    @ManyToOne
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    private Property property;
}
