package service


import org.springframework.security.core.Authentication
import spock.lang.Specification
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

    // createLocation method is returning null value. Authentication is not passing in the parameter
    // I think that is why this method returning a null value

    def "createLocation should save and return the location DTO"() {

        authentication.getPrincipal() >> user
        LocationRequest request = new LocationRequest(
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA"
        )
        def saveLocation = locationRepo.save(_)
        locationMapper.toLocation(request) >> new Location()
        locationMapper.toDto(saveLocation as Location)


        when:
        def result = locationServiceImpl.createLocation(request, authentication)

        then:
        1 * locationRepo.save(_)
        result.locationName == request.locationName
        result.streetNumAndName == request.streetNumAndName
        result.apartmentNum == request.apartmentNum
        result.city == request.city
        result.state == request.state
        result.country == request.country
    }
    def "should return a list of users with the given location "() {
        given:
        def  user1 = new User(id: 1l, email: "nick@gmail.com", password: "123")
        def user2 = new User(id: 2l, email: "andrew@gmail.com", password: "432")
        def location1 = new Location(
                id: 1l, owner: user1,
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")
        def location2 = new Location(
                id: 12, owner: user2,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
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
                id: 1l, owner: user1,
                locationName: "Home",
                streetNumAndName: "123 Main St",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")
        def location2 = new Location(
                id: 12, owner: user,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")

        SharedLocation sharedLocation =
                new SharedLocation(id: 1l,sender: user1, receiver: user, location: location2,accessible:Accessible.TRUE )
        locationRepo.findAllByOwner(_) >> [location1,location2]
        sharedLocationRepo.findAllByReceiver(_) >> [sharedLocation]
        authentication.getPrincipal() >> user
        when:
        List<LocationDto> result = locationServiceImpl.getAllLocation(authentication)

        then:
        result.size() == 2
        result.each {
            LocationDto locationDto -> locationDto != null
        }
    }
    def "changeAccess should change access for location" (){
        given:
        def request = new AccessRequest(sharedLocationId: 1l,accessible: Accessible.FALSE)
        def location2 = new Location(
                id: 12, owner: user,
                locationName: "Work",
                streetNumAndName: "321 Blvd ",
                apartmentNum: "1A",
                city: "New York",
                state: "NY",
                country: "USA")


            def sharedLocation = new SharedLocation(
                    id: 1l, sender: user1, receiver:
                    user, location: location2, accessible: Accessible.TRUE)
        SharedLocationRepo stubbedRepo = Stub(SharedLocationRepo)
        stubbedRepo.findById(_).orElse(sharedLocation) >> sharedLocation

        when:
        locationServiceImpl.changeAccess(request)

        then:
        sharedLocation.accessible == Accessible.FALSE
    }
}