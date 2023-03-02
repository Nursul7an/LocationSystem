package test.locationsystem.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import test.locationsystem.model.enums.Accessible;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SharedLocationDto {
    Long id;
    UserDto receiver;
    LocationDto locationDto;
    Accessible accessible;
}
