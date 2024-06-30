//package gr.aueb.cf.masterpeakers.model;
//
//public enum Role {
//    USER,
//    ADMIN
//}


package gr.aueb.cf.masterpeakers.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable = false, unique = true)
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public static final Role ROLE_USER = new Role("ROLE_USER");
    public static final Role ROLE_ADMIN = new Role("ROLE_ADMIN");
}