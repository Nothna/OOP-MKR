package com.example.carsharing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import com.example.carsharing.dataWriter.UserData;
import com.example.carsharing.dto.CreateUserDto;
import com.example.carsharing.userModule.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.hamcrest.Matchers.matchesPattern;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserData userData;


    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testRegisterSuccess() throws Exception {
        // Generate unique email for each test
        String uniqueEmail = "user" + System.currentTimeMillis() + "@example.com";

        // Mock UserData behavior
        User mockUser = new User(1, "nickname", uniqueEmail, "password");
        when(userData.create(any(CreateUserDto.class))).thenReturn(mockUser);

        CreateUserDto newUser = new CreateUserDto("nickname", uniqueEmail, "password");
        String newUserJson = asJsonString(newUser);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isOk())
                .andExpect(content().string(matchesPattern("^[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]*$")));
    }


    @Test
    void testRegisterFailure() throws Exception {
        // Mock UserData to throw an exception
        when(userData.create(any(CreateUserDto.class))).thenThrow(new RuntimeException("Error"));

        CreateUserDto newUser = new CreateUserDto("nickname", "test@example.com", "password");
        String newUserJson = asJsonString(newUser);


        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUserJson))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Error: Incorrect register credentials"));
    }

 
}
