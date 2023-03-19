package home.four.you.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Entity class for User model.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Ad> ads = new ArrayList<>();

    public Collection<SimpleGrantedAuthority> getAuthorities() {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        authorities.addAll(
                role.getPrivileges().stream()
                        .map(privilege -> new SimpleGrantedAuthority(privilege.getName().name()))
                        .collect(Collectors.toSet()));
        return authorities;
    }
}
