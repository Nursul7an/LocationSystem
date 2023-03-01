package test.locationsystem.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LocationRequest {
    @NotBlank
    String locationName;
    @NotBlank
    String streetNumAndName;
    @NotBlank
    String apartmentNum;
    @NotBlank
    String city;
    @NotBlank
    String state;
    @NotBlank
    String country;
}
