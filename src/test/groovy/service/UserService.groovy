package service

import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll
import test.locationsystem.mapper.UserMapper
import test.locationsystem.model.dto.UserDto
import test.locationsystem.model.dto.request.UserRegisterRequest
import test.locationsystem.repository.UserRepo
import test.locationsystem.service.impls.UserServiceImpl

class UserService extends Specification{
    UserRepo userRepo = Mock()
    PasswordEncoder passwordEncoder = Mock()
    UserMapper userMapper = Mock()

    @Subject
    UserServiceImpl userService = new UserServiceImpl(userRepo,passwordEncoder)

    @Unroll
    def "registerUser should return a valid UserDto when given valid request #scenario"() {
        given:
        def request = new UserRegisterRequest(
                email: "60.nursultank@gmail.com",
                password: "password123"
        )
        request.setPassword(passwordEncoder.encode(request.password))
        def user = userMapper.toUser(request)
        userRepo.save(user)
        def expectedDto = new UserDto()

        when:
        def result = null
        try {
            result = userService.registerUser(request)
        } catch (Exception e) {
            println("Exception occurred: " + e.printStackTrace())
            fail("registerUser method threw an exception")
        }

        then:
        userRepo.existsUserByEmail(request.email) >> false
        userMapper.toDto(user) >> expectedDto
        result == expectedDto
    }


}
