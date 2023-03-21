package test.locationsystem.service.impls;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import test.locationsystem.exception.InvalidEmailException;
import test.locationsystem.mapper.UserMapper;
import test.locationsystem.model.dto.UserDto;
import test.locationsystem.model.dto.request.UserRegisterRequest;
import test.locationsystem.model.entity.User;
import test.locationsystem.repository.UserRepo;
import test.locationsystem.service.UserService;

import java.util.regex.Pattern;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    public  UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = UserMapper.INSTANCE;
    }

    @Override
    public UserDto registerUser(UserRegisterRequest request) {
        if (!validateEmail(request.getEmail()))
            throw new InvalidEmailException("Invalid email address or email already exist");

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = userMapper.toUser(request);
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

    public boolean validateEmail(String email){
        if (userRepo.existsUserByEmail(email))
            throw new InvalidEmailException("The user already exist with email "+ email );

        return Pattern.compile("^(.+)@(.+)$")
                .matcher(email)
                .matches();
    }

}
