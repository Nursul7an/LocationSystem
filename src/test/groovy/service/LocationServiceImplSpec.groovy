package service


import org.springframework.security.core.Authentication
import spock.lang.Specification
import test.locationsystem.exception.NotFoundException
import test.locationsystem.mapper.LocationMapper
import test.locationsystem.mapper.UserMapper
import test.locationsystem.mapper.impls.SharedLocationMapperImpl
import test.locationsystem.model.dto.LocationDto
import test.locationsystem.model.dto.UserDto
import test.locationsystem.model.dto.request.AccessRequest
import test.locationsystem.model.dto.request.LocationRequest
import test.locationsystem.model.entity.Location
import test.locationsystem.model.entity.SharedLocation
import test.locationsystem.model.entity.User
import test.locationsystem.model.enums.Accessible
import test.locationsystem.repository.LocationRepo
import test.locationsystem.repository.SharedLocationRepo
import test.locationsystem.service.impls.LocationServiceImpl

class LocationServiceImplSpec extends Specification {
    LocationServiceImpl locationServiceImpl;
    LocationRepo locationRepo = Mock(LocationRepo)
    LocationMapper locationMapper = Mock(LocationMapper)
    SharedLocationRepo sharedLocationRepo = Mock(SharedLocationRepo)
    UserMapper userMapper = Mock(UserMapper)
    SharedLocationMapperImpl sharedLocationMapper = Mock(SharedLocationMapperImpl)
    Authentication authentication = Mock(Authentication)

    void setup() {
        locationServiceImpl = new LocationServiceImpl(locationRepo,sharedLocationMapper, sharedLocationRepo)
        locationServiceImpl.locationMapper = locationMapper
        locationServiceImpl.userMapper = userMapper
    }
    User user = new User(id: 1l,email: "nick@gmail.com", password: "123456")
    def  user1 = new User(id: 1l, email: "nick@gmail.com", password: "123")


    def "createLocation should save and return the locationDto"() {

        given:
         LocationRequest request = new LocationRequest(
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
               country: "USA"
        )
        authentication.getPrincipal() >> user
        def location = locationMapper.toLocation(request)
        locationMapper.toDto(_) >> new LocationDto()
        locationRepo.save(_) >> location

        when:
        def result = locationServiceImpl.createLocation(request, authentication)

        then:
        result.locationName == request.locationName
        result.streetNumAndName == request.streetNumAndName
        result.apartmentNum == request.apartmentNum
        result.city == request.city
        result.state == request.state
    }
    def "should return a list of users with the given location "() {
        given:
        def  user1 = new User(id: 1, email: "nick@gmail.com", password: "123")
        def user2 = new User(id: 2, email: "andrew@gmail.com", password: "432")
        def location1 = new Location(
                id: 1, owner: user1,
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "2A",
                city: "New York",
                state: "NY",
                country: "USA")
        def location2 = new Location(
                id: 12, owner: user2,
                locationName: "Work",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")
        locationRepo.findAllLocation(_) >> [location1, location2]
        userMapper.toDto(_) >> new UserDto()

        when:
        List<UserDto> result = locationServiceImpl.getUserByLocation("123 Main St")

        then:
        result.size() == 2
        result.each { UserDto userDto ->
            userDto != null
        }
    }
    def "Find location by user" (){
        given:
        def location1 = new Location(
                id: 1L, owner: user1,
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")
        def location2 = new Location(
                id: 12L, owner: user,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")

        SharedLocation sharedLocation =
                new SharedLocation(id: 1L,sender: user1, receiver: user, location: location2,accessible:Accessible.TRUE )
        locationRepo.findAllByOwner(user) >> [location1,location2]
        sharedLocationRepo.findAllByReceiver(user) >> [sharedLocation]
        locationMapper.toDto(location1) >> new LocationDto(
                id: 1L,
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA"
        )
        locationMapper.toDto(location2) >> new LocationDto(
                id: 12L,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")
        locationMapper.toDto(sharedLocation.getLocation()) >> new LocationDto(
                id: 12L,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")

        authentication.getPrincipal() >> user

        when:
        List<LocationDto> result = locationServiceImpl.getAllLocation(authentication)

        then:
        result.size() == 2
        result.each {
            LocationDto locationDto -> locationDto != null
        }
    }
    def "It should throw NotFoundException" (){
        given:
        def request = new AccessRequest(sharedLocationId: 1,accessible: Accessible.FALSE)
        def location2 = new Location(
                id: 12, owner: user,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")
        def stubbedRepo = Stub(SharedLocationRepo){
            sharedLocationRepo.findById(2) >>
                    new SharedLocation(id: 1, sender: user1, receiver: user, location: location2, accessible: Accessible.TRUE)
        }
        and:
        def service = new LocationServiceImpl(locationRepo,sharedLocationMapper,stubbedRepo)
        when:

        service.changeAccess(request)

        then:
        thrown(NotFoundException)
    }

}