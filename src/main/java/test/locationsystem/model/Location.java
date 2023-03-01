package test.locationsystem.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "locations")
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
}
