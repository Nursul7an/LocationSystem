package service

import spock.lang.Specification
import spock.lang.Subject
import test.locationsystem.mapper.LocationMapper
import test.locationsystem.mapper.SharedLocationMapper
import test.locationsystem.mapper.UserMapper
import test.locationsystem.repository.LocationRepo
import test.locationsystem.repository.SharedLocationRepo
import test.locationsystem.service.impls.LocationServiceImpl

class LocationService extends Specification{
    LocationRepo locationRepo = Mock()
    LocationMapper locationMapper = Mock()
    SharedLocationRepo sharedLocationRepo = Mock()
    SharedLocationMapper sharedLocationMapper = Mock()
    UserMapper userMapper = Mock()

    @Subject
    LocationServiceImpl locationService = new LocationServiceImpl(locationRepo,sharedLocationMapper,sharedLocationRepo)


}
