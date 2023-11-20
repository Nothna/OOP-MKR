package com.example.carsharing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import com.example.carsharing.dataWriter.UserData;
import com.example.carsharing.dto.CreateUserDto;
import com.example.carsharing.userModule.User;

@ExtendWith(MockitoExtension.class)
public class UserDataTest {

    @Mock
    private FileReader fileReader;

    @Mock
    private FileWriter fileWriter;

    @Mock
    private JSONParser jsonParser;

    @InjectMocks
    private UserData userData;

    private JSONArray usersArray;
    private JSONObject userObject;
    private CreateUserDto createUserDto;

    @BeforeEach
    void setUp() throws Exception {
        usersArray = new JSONArray();
        userObject = new JSONObject();
        userObject.put("id", 1);
        userObject.put("nickname", "TestUser");
        userObject.put("email", "test@example.com");
        userObject.put("password", "hashedpassword");
        usersArray.add(userObject);

        createUserDto = new CreateUserDto("TestUser", "test@example.com", "password");

        when(jsonParser.parse(any(FileReader.class))).thenReturn(usersArray);
        when(fileReader.ready()).thenReturn(true);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void testGetUsers() throws Exception {
        JSONArray result = userData.get();
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testGetUserById() throws Exception {
        JSONObject result = userData.get(1L); // if the 'get' method expects a long
        assertNotNull(result);
        assertEquals("TestUser", result.get("nickname"));
    }


    @Test
    void testGetUserByEmail() throws Exception {
        JSONObject result = userData.get("test@example.com");
        assertNotNull(result);
        // Cast the expected integer to a long since JSON stores it as a long
        assertEquals(1L, result.get("id"));
    }


    @Test
    void testCreateUser() throws Exception {
        User result = userData.create(createUserDto);
        assertNotNull(result);
        assertEquals("TestUser", result.getNickname());
        // Assert that the password is hashed
        assertNotEquals("password", result.getPassword());
    }

    @Test
    void testUpdateUser() throws Exception {
        JSONObject updatedUser = new JSONObject();
        updatedUser.put("nickname", "UpdatedUser");

        userData.update(1, updatedUser);

        JSONObject result = userData.get(1);
        assertEquals("UpdatedUser", result.get("nickname"));
    }

    @Test
    void testDeleteUser() throws Exception {
        userData.delete(1);

        JSONObject result = userData.get(1);
        assertNull(result);
    }

}
