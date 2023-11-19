package com.example.carsharing;

import static org.junit.jupiter.api.Assertions.*;
import org.json.simple.parser.JSONParser;
import com.example.carsharing.dataWriter.UserData;
import com.example.carsharing.dto.CreateUserDto;
import com.example.carsharing.userModule.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.mockito.Mockito;

@ExtendWith(MockitoExtension.class)
public class UserDataTest {

    @Mock
    private FileReader mockFileReader;

    @Mock
    private FileWriter mockFileWriter;

    @Mock
    private JSONParser mockParser;

    @InjectMocks
    private UserData userData;

    private CreateUserDto newUserDto;
    private JSONObject userObject;
    private JSONArray usersArray;

    @BeforeEach
    public void setUp() throws Exception {
        // Initialize user DTO
        newUserDto = new CreateUserDto("test@example.com", "TestUser", "password123");

        // Initialize JSON objects to simulate users
        userObject = new JSONObject();
        userObject.put("id", 1);
        userObject.put("email", "test@example.com");
        userObject.put("nickname", "TestUser");
        userObject.put("password", "hashedpassword");

        usersArray = new JSONArray();
        usersArray.add(userObject);

        // Setup Mocks
        when(mockParser.parse(any(FileReader.class))).thenReturn(usersArray);
        doNothing().when(mockFileWriter).write(anyString());
        doNothing().when(mockFileWriter).flush();
    }


    @AfterEach
    public void tearDown() {
        Mockito.reset(mockFileReader, mockFileWriter, mockParser);
    }

    @Test
    public void testGetUsers() throws Exception {
        JSONArray result = userData.get();
        assertNotNull(result);
        assertEquals(1, result.size());
        // Add more assertions as necessary
    }

    @Test
    public void testGetUserById() throws Exception {
        JSONObject result = userData.get(1);
        assertNotNull(result);
        assertEquals("test@example.com", result.get("email"));
        // Add more assertions as necessary
    }

    @Test
    public void testGetUserByEmail() throws Exception {
        JSONObject result = userData.get("test@example.com");
        assertNotNull(result);
        assertEquals(1, result.get("id"));
        // Add more assertions as necessary
    }

    @Test
    public void testCreateUser() throws Exception {
        User result = userData.create(newUserDto);
        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        // Add more assertions as necessary
    }

    @Test
    public void testUpdateUser() throws Exception {
        JSONObject updatedUser = new JSONObject();
        updatedUser.put("nickname", "UpdatedTestUser");

        userData.update(1, updatedUser);

        // Verify save method was called
        verify(mockFileWriter, times(1)).write(anyString());
        // Additional verification and assertions as needed
    }

    @Test
    public void testDeleteUser() throws Exception {
        userData.delete(1);

        // Verify save method was called
        verify(mockFileWriter, times(1)).write(anyString());
        // Additional verification and assertions as needed
    }



}
