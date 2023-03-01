package test.locationsystem.mapper.impls;

import org.springframework.stereotype.Component;
import test.locationsystem.mapper.LocationMapper;
import test.locationsystem.mapper.SharedLocationMapper;
import test.locationsystem.mapper.UserMapper;
import test.locationsystem.model.dto.SharedLocationDto;
import test.locationsystem.model.dto.request.ShareLocationRequest;
import test.locationsystem.model.entity.SharedLocation;
import test.locationsystem.repository.LocationRepo;
import test.locationsystem.repository.UserRepo;

@Component

public class SharedLocationMapperImpl implements SharedLocationMapper {
    private final UserRepo userRepo;
    private final LocationRepo locationRepo;
    private final LocationMapper locationMapper;
    private final UserMapper userMapper;

    public SharedLocationMapperImpl(UserRepo userRepo, LocationRepo locationRepo) {
        this.userRepo = userRepo;
        this.locationRepo = locationRepo;
        this.userMapper = UserMapper.INSTANCE;
        this.locationMapper = LocationMapper.INSTANCE;
    }

    @Override
    public SharedLocation toSharedLocation(ShareLocationRequest request) {
        SharedLocation sharedLocation = new SharedLocation();
        sharedLocation.setReceiver(userRepo.getById(request.getLocationId()));
        sharedLocation.setLocation(locationRepo.getById(request.getLocationId()));
        return sharedLocation;
    }

    @Override
    public SharedLocationDto toDto(SharedLocation savedUser) {
        SharedLocationDto sharedLocationDto = new SharedLocationDto();
        sharedLocationDto.setId(savedUser.getId());
        sharedLocationDto.setLocationDto(locationMapper.toDto(savedUser.getLocation()));
        sharedLocationDto.setReceiver(userMapper.toDto(savedUser.getReceiver()));
        return sharedLocationDto;
    }
}
