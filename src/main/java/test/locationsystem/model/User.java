package test.locationsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "password", nullable = false)
    String password;
}
