package test.locationsystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import test.locationsystem.model.dto.LocationDto;
import test.locationsystem.model.dto.request.LocationRequest;
import test.locationsystem.model.entity.Location;
import test.locationsystem.model.entity.User;

@Mapper
public interface LocationMapper {
    LocationMapper INSTANCE = Mappers.getMapper( LocationMapper.class );


    Location toLocation(LocationRequest locationRequest);

    LocationDto toDto(Location savedLocation);
}
