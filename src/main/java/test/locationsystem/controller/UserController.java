package test.locationsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.UserRegisterRequest;
import test.locationsystem.service.UserService;

import javax.mail.internet.AddressException;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserRegisterRequest request) throws AddressException {
        return ResponseEntity.ok(userService.registerUser(request));
    }
}
