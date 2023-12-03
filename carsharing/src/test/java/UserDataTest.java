import com.example.carsharing.Users.User;
import com.example.carsharing.Users.dto.CreateUserDto;
import com.example.carsharing.shared.Car;
import com.example.carsharing.shared.Rental;
import com.example.carsharing.shared.dataWriter.UserData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class UserDataTest {

    private UserData userData;
    private ObjectMapper mockedMapper;
    private PasswordEncoder mockedPasswordEncoder;

    @BeforeEach
    void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        mockedMapper = mock(ObjectMapper.class);
        mockedPasswordEncoder = mock(PasswordEncoder.class);

        userData = new UserData();

        Field mapperField = UserData.class.getDeclaredField("objectMapper");
        mapperField.setAccessible(true);
        mapperField.set(userData, mockedMapper);

        Field encoderField = UserData.class.getDeclaredField("passwordEncoder");
        encoderField.setAccessible(true);
        encoderField.set(userData, mockedPasswordEncoder);
    }

    @Test
    void testGetUsers() throws IOException {
        List<User> expectedUsers = Arrays.asList(
                new User(1L, "Nick1", "hashedPass1", "email1@example.com"),
                new User(2L, "Nick2", "hashedPass2", "email2@example.com")
        );

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(expectedUsers);

        List<User> result = userData.get();

        assertEquals(expectedUsers, result);
    }

    @Test
    void testGetUserById() throws IOException {
        List<User> users = Arrays.asList(
                new User(1L, "Nick1", "hashedPass1", "email1@example.com"),
                new User(2L, "Nick2", "hashedPass2", "email2@example.com")
        );

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(users);

        User result = userData.get(1L);

        assertNotNull(result);
        assertEquals(1, result.getId());
    }
    @Test
    void testGetUserByEmail() throws IOException {
        List<User> users = Arrays.asList(
                new User(1L, "Nick1", "hashedPass1", "email1@example.com"),
                new User(2L, "Nick2", "hashedPass2", "email2@example.com")
        );

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(users);

        User result = userData.get("email1@example.com");

        assertNotNull(result);
        assertEquals("email1@example.com", result.getEmail());
    }
    /*@Test
    void testOrder() throws IOException {
        Car testCar = new Car("KIA", "Cee`d", 2017, 4, "4x2");
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusDays(1);
        Rental testRental = new Rental(testCar, startTime, endTime);

        User testUser = new User(1L, "Username", "password", "user@example.com");
        List<User> users = new ArrayList<>();
        users.add(testUser);

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(users);
        doNothing().when(mockedMapper).writeValue(any(File.class), anyList());

        User result = userData.order(1L, testRental);

        assertNotNull(result);
        assertEquals(testRental, result.getRental());
    }*/
    @Test
    void testCreateUser() throws IOException {
        List<User> users = new ArrayList<>();

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(users);
        doNothing().when(mockedMapper).writeValue(any(File.class), anyList());
        when(mockedPasswordEncoder.encode(anyString())).thenReturn("hashedPassword");

        CreateUserDto newUserDto = new CreateUserDto();
        newUserDto.setNickname("Nick");
        newUserDto.setPassword("password");
        newUserDto.setEmail("email@example.com");

        User result = userData.create(newUserDto);

        assertNotNull(result);
        assertEquals("email@example.com", result.getEmail());
    }

    @Test
    void testDeleteUser() throws IOException {
        List<User> users = new ArrayList<>();
        User user = new User(1L, "Nick", "hashedPass", "email@example.com");
        users.add(user);

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(users);
        doNothing().when(mockedMapper).writeValue(any(File.class), anyList());

        userData.delete(1L);

        assertTrue(users.isEmpty());
    }

}
