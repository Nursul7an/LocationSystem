package test.locationsystem.model.dto.request;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRegisterRequest {
    @NotNull
    String email;
    @NotNull
    String password;
}
