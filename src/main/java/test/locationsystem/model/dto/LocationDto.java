package test.locationsystem.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationDto {
    Long id;
    String locationName;
    String streetNumAndName;
    String apartmentNum;
    String city;
    String state;
    String country;
}
