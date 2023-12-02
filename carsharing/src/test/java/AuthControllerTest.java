import com.example.carsharing.Auth.AuthController;
import com.example.carsharing.Auth.dto.SignUserDto;
import com.example.carsharing.Users.User;
import com.example.carsharing.Users.dto.CreateUserDto;
import com.example.carsharing.shared.dataWriter.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private UserData userData;

    @InjectMocks
    private AuthController authController;


    @Test
    void testSuccessfulRegistration() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto(); // Populate with test data
        User mockUser = new User(); // Populate with test data
        when(userData.create(any(CreateUserDto.class))).thenReturn(mockUser);

        ResponseEntity<?> response = authController.register(createUserDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    void testRegistrationWithExistingUser() throws IOException {
        CreateUserDto createUserDto = new CreateUserDto(); // Populate with test data
        when(userData.create(any(CreateUserDto.class))).thenThrow(new IOException("User already exists"));

        ResponseEntity<?> response = authController.register(createUserDto);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Error: Incorrect register credentials", response.getBody());
    }


    @Test
    void testSignInWithValidCredentials() throws Exception {
        SignUserDto signInUserDto = new SignUserDto("test@example.com", "password123"); // Populate with test data

        User mockUser = new User();
        when(userData.get(anyString())).thenReturn(mockUser);

        ResponseEntity<?> response = authController.register(signInUserDto);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody()); // Check if the response body (JWT token) is not null
    }

    @Test
    void testGetPassword() {
        String expectedPassword = "testPassword";
        SignUserDto signUserDto = new SignUserDto("test@example.com", expectedPassword);

        String actualPassword = signUserDto.getPassword();

        assertEquals(expectedPassword, actualPassword, "The getPassword method should return the correct password.");
    }
}