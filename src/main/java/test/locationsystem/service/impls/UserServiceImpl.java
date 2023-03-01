package test.locationsystem.service.impls;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.locationsystem.mapper.UserMapper;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.UserRegisterRequest;
import test.locationsystem.model.entity.User;
import test.locationsystem.repository.UserRepo;
import test.locationsystem.service.UserService;

import javax.mail.internet.AddressException;
import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final   UserRepo userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = UserMapper.INSTANCE;
    }


    @Override
    public UserDto registerUser(UserRegisterRequest request) {
        if(!validateEmail(request.getEmail()))
            throw new RuntimeException("Invalid email address or email already exist");

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userMapper.toUser(request);
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    private boolean validateEmail(String email){
        if (userRepo.existsUserByEmail(email))
            throw new RuntimeException("The user already exist with email "+ email );

        return Pattern.compile("^(.+)@(.+)$")
                .matcher(email)
                .matches();
    }
}
