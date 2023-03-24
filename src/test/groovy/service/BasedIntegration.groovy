package service

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock
import service.LocationServiceImplSpec
import spock.lang.Specification

@SpringBootTest(classes = LocationServiceImplSpec.class)
@AutoConfigureWireMock(port = 0)
abstract class BasedIntegration extends Specification{
}
