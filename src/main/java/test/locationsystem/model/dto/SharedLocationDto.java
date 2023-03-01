package test.locationsystem.model.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SharedLocationDto {
    Long id;
    UserDto sender;
    UserDto receiver;

}
