package test.locationsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.UserRegisterRequest;
import test.locationsystem.model.entity.User;

@Mapper

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
    User toUser(UserRegisterRequest request);
    UserDto toDto(User user);
}
