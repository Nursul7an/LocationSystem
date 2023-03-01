package test.locationsystem.service;

import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.UserRegisterRequest;

import javax.mail.internet.AddressException;

public interface UserService {
    UserDto registerUser(UserRegisterRequest request) throws AddressException;
}
