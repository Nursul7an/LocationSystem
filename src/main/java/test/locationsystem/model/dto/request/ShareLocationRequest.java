package test.locationsystem.model.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import test.locationsystem.model.enums.Accessible;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShareLocationRequest {
    Long receiverId;
    Long LocationId;
    Accessible accessible;
}
