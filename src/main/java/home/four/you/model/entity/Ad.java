package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

/**
 * Entity class for Ad model.
 */
@Entity
@Table(name = "ads")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Accessors(chain = true)
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

    @Column
    private Integer price;

    @Column(name = "created_at")
    @CreatedDate
    private Instant createdAt;

    @Column(name = "expiration_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Instant expirationDate;

    @Column
    private Boolean expired = false;

    @OneToOne(mappedBy = "ad", cascade = CascadeType.ALL)
    private Property property;

    @ManyToOne
    private User owner;

    public Ad setProperty(Property property) {
        this.property = property;
        property.setAd(this);

        return this;
    }
}
