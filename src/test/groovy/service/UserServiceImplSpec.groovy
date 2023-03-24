package test.locationsystem.service.impls

import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import test.locationsystem.exception.InvalidEmailException
import test.locationsystem.mapper.UserMapper
import test.locationsystem.model.dto.UserDto
import test.locationsystem.model.dto.request.UserRegisterRequest
import test.locationsystem.model.entity.User
import test.locationsystem.repository.UserRepo

class UserServiceImplSpec extends Specification {

    UserServiceImpl userServiceImpl
    PasswordEncoder passwordEncoder = Mock(PasswordEncoder)
    UserMapper userMapper = Mock(UserMapper)
    UserRepo userRepo = Mock(UserRepo)

    void setup() {
        userServiceImpl = new UserServiceImpl(userRepo, passwordEncoder)
        userServiceImpl.userMapper = userMapper
    }
    def "Register new user"() {
        given:
        def email = "nick@gmail.com"
        def password = "123"
        def request = new UserRegisterRequest(email: email, password: password)

        userMapper.toUser(request) >> new User()
        userRepo.save(_) >> new User()
        userMapper.toDto(_) >> new UserDto(id: 1L, email: email)

        when:
        def result = userServiceImpl.registerUser(request)

        then:
        1* userMapper.toUser(request)
        1* userRepo.save(_) >> new User()
        result != null
        result.email == email
        result.id == 1L
    }
    def "Validate email" (){
        given:
        def email = "nick@gmail.com"
        userRepo.existsUserByEmail(_) >> true

        when:
        userServiceImpl.validateEmail(email)

        then:
        thrown(InvalidEmailException)
    }

}