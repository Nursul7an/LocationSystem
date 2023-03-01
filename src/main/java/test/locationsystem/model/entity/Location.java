package test.locationsystem.model.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "locations")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Location {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String locationName;
    String streetNumAndName;
    String apartmentNum;
    String city;
    String state;
    String country;
    @ManyToOne
            @JoinColumn(name = "owner_id")
    User owner;
}
