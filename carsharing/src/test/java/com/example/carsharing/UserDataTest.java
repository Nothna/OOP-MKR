/*
package com.example.carsharing;
import com.example.carsharing.dataWriter.UserData;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;

import static org.junit.jupiter.api.Assertions.*;

public class UserDataTest {

    private static final String TEST_FILE_PATH = "testData.json";
    private UserData userData;

    @BeforeEach
    public void setUp() throws Exception {
        // Створення прикладу JSON масиву
        JSONArray usersArray = new JSONArray();
        JSONObject user1 = new JSONObject();
        user1.put("id", 1L);
        user1.put("email", "test1@example.com");
        usersArray.add(user1);

        // Запис даних до тестового файлу
        try (FileWriter file = new FileWriter(TEST_FILE_PATH)) {
            file.write(usersArray.toJSONString());
            file.flush();
        }

        userData = new UserData(TEST_FILE_PATH);
    }

    @Test
    public void testGetById() throws Exception {
        JSONObject user = userData.get(1L);
        assertNotNull(user);
        assertEquals("test1@example.com", user.get("email"));
    }

    @Test
    public void testGetByEmail() throws Exception {
        JSONObject user = userData.get("test1@example.com");
        assertNotNull(user);
        assertEquals(1L, user.get("id"));
    }

    @Test
    public void testGetAll() throws Exception {
        JSONArray users = userData.get();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    */
/*@Test
    public void testCreate() throws Exception {
        User newUser = new User();

        dataWriter.create(newUser);
        JSONObject retrievedUser = dataWriter.get("test2@example.com");
        assertNotNull(retrievedUser);
        assertEquals(2L, retrievedUser.get("id"));
    }*//*


    @Test
    public void testUpdate() throws Exception {
        JSONObject updatedUser = new JSONObject();
        updatedUser.put("id", 1L);
        updatedUser.put("email", "updated@example.com");

        userData.update(1L, updatedUser);
        JSONObject retrievedUser = userData.get("updated@example.com");
        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.get("id"));
    }

    @Test
    public void testDelete() throws Exception {
        userData.delete(1L);
        JSONObject user = userData.get(1L);
        assertNull(user);
    }

    // Після виконання тестів видалити тестовий файл
    @AfterEach
    public void tearDown() throws Exception {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }
    */
/*@Test
    public void testCreateWithExistingEmail() {
        JSONObject newUser = new JSONObject();
        newUser.put("id", 3L);
        newUser.put("email", "test1@example.com");

        Exception exception = assertThrows(Exception.class, () -> {
            dataWriter.create(newUser);
        });

        String expectedMessage = "Користувач з таким email вже існує";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }*//*

}
*/
