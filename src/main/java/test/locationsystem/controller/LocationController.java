package test.locationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import test.locationsystem.model.dto.LocationDto;
import test.locationsystem.model.dto.SharedLocationDto;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.LocationRequest;
import test.locationsystem.model.dto.request.ShareLocationRequest;
import test.locationsystem.model.entity.User;
import test.locationsystem.service.LocationService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    @PostMapping("/create")
    public ResponseEntity<LocationDto> addLocation(@Valid @RequestBody LocationRequest locationRequest,
                                                   Authentication authentication){
        return ResponseEntity.ok(locationService.createLocation(locationRequest,authentication));
    }
    @PostMapping("/share")
    public ResponseEntity<SharedLocationDto> shareLocation(@RequestBody ShareLocationRequest request,
                                                           Authentication authentication){
        return ResponseEntity.ok(locationService.share(request, authentication));
    }
    @GetMapping("get/users")
    public ResponseEntity<List<UserDto>> getUserByLocation(@RequestParam String value){
        return ResponseEntity.ok(locationService.getUserByLocation(value));
    }
}
