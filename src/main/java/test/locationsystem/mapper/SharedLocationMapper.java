package test.locationsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import test.locationsystem.model.dto.SharedLocationDto;
import test.locationsystem.model.dto.request.ShareLocationRequest;
import test.locationsystem.model.entity.SharedLocation;


public interface SharedLocationMapper {

    SharedLocation toSharedLocation(ShareLocationRequest request);

    SharedLocationDto toDto(SharedLocation savedUser);
}
