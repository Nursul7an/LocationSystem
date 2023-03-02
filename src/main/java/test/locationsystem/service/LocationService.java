package test.locationsystem.service;

import org.springframework.security.core.Authentication;
import test.locationsystem.model.dto.LocationDto;
import test.locationsystem.model.dto.SharedLocationDto;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.AccessRequest;
import test.locationsystem.model.dto.request.LocationRequest;
import test.locationsystem.model.dto.request.ShareLocationRequest;

import java.util.List;

public interface LocationService {
    LocationDto createLocation(LocationRequest locationRequest, Authentication authentication);

    SharedLocationDto share(ShareLocationRequest request, Authentication authentication);

    List<UserDto> getUserByLocation(String value);

    void changeAccess(AccessRequest accessRequest);

    List<LocationDto> getAllLocation(Authentication authentication);
}
