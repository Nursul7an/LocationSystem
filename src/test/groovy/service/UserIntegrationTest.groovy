package service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import test.locationsystem.model.dto.UserDto
import test.locationsystem.model.dto.request.UserRegisterRequest

@AutoConfigureMockMvc
class  UserIntegrationTest extends BasedIntegration{
     @Autowired
     ObjectMapper objectMapper
     @Autowired
     MockMvc mockMvc

    def "create user and return userDto"(){
        given:
        def request = new UserRegisterRequest(email: "nick@gmail.com", password: "123")

        when:
        def response = objectMapper.readValue(mockMvc.perform(
            MockMvcRequestBuilders.post("user/register")
        .content(objectMapper.writeValueAsString(request))
        .contentType(MediaType.APPLICATION_JSON_VALUE))
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andReturn()
        .response
        .contentAsString, UserDto )

        then:
       with(response){
           email == "nick@gmail.com"
       }
    }
}
