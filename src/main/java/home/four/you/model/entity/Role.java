package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Entity class for Role model.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role {

    /**
     * Enumeration for authority role type.
     */
    public enum AuthorityRole {

        /**
         * User has admin privileges.
         */
        ROLE_ADMIN,

        /**
         * User has regular privileges.
         */
        ROLE_USER
    }

    @Id
    @GeneratedValue
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private AuthorityRole role;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "roles_privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}
