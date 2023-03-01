package test.locationsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "shared_locations")
public class SharedLocation {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
            @JoinColumn(name = "sender_id")
    User sender;
    @ManyToOne
            @JoinColumn(name = "receiver_id")
    User receiver;

}
