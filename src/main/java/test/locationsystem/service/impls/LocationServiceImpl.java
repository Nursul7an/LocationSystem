package test.locationsystem.service.impls;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import test.locationsystem.mapper.LocationMapper;
import test.locationsystem.mapper.SharedLocationMapper;
import test.locationsystem.mapper.UserMapper;
import test.locationsystem.model.dto.LocationDto;
import test.locationsystem.model.dto.SharedLocationDto;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.LocationRequest;
import test.locationsystem.model.dto.request.ShareLocationRequest;
import test.locationsystem.model.entity.Location;
import test.locationsystem.model.entity.SharedLocation;
import test.locationsystem.model.entity.User;
import test.locationsystem.repository.LocationRepo;
import test.locationsystem.repository.SharedLocationRepo;
import test.locationsystem.service.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationMapper locationMapper;
    private final LocationRepo locationRepo;
    private final SharedLocationMapper sharedLocationMapper;
    private final SharedLocationRepo sharedLocationRepo;
    private final UserMapper userMapper;

    public LocationServiceImpl(LocationRepo locationRepo, SharedLocationMapper sharedLocationMapper, SharedLocationRepo sharedLocationRepo) {
        this.locationRepo = locationRepo;
        this.sharedLocationMapper = sharedLocationMapper;
        this.sharedLocationRepo = sharedLocationRepo;
        this.userMapper = UserMapper.INSTANCE;
        this.locationMapper = LocationMapper.INSTANCE;
    }

    @Override
    public LocationDto createLocation(LocationRequest locationRequest, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Location location = locationMapper.toLocation(locationRequest);
        location.setOwner(user);
        Location savedLocation = locationRepo.save(location);
        return locationMapper.toDto(savedLocation);
    }

    @Override
    public SharedLocationDto share(ShareLocationRequest request, Authentication authentication) {
        SharedLocation sharedLocation = sharedLocationMapper.toSharedLocation(request);
        User user = (User) authentication.getPrincipal();
        sharedLocation.setSender(user);

        SharedLocation savedUser = sharedLocationRepo.save(sharedLocation);
        return sharedLocationMapper.toDto(savedUser);
    }

    @Override
    public List<UserDto> getUserByLocation(String value) {
        List<Location> locations = locationRepo.findAllLocation(value);
        List<User> userList = new ArrayList<>();
        for (Location obj: locations){
            userList.add(obj.getOwner());
        }
        return userList.stream().map(userMapper::toDto).collect(Collectors.toList());
    }

}