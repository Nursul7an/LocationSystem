package test.locationsystem.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import test.locationsystem.model.enums.Accessible;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "shared_locations")
@FieldDefaults(level = AccessLevel.PRIVATE)
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
    @ManyToOne
            @JoinColumn(name = "location_id")
    Location location;
    @Enumerated(EnumType.STRING)
    Accessible accessible;

}
