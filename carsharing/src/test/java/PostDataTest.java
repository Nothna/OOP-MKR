import com.example.carsharing.Posts.Post;
import com.example.carsharing.Posts.dto.CreatePostDto;
import com.example.carsharing.shared.Car;
import com.example.carsharing.shared.dataWriter.PostData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PostDataTest {

    private PostData postData;
    private ObjectMapper mockedMapper;

    @BeforeEach
    public void setUp() throws IOException, NoSuchFieldException, IllegalAccessException {
        postData = new PostData();
        mockedMapper = mock(ObjectMapper.class);
        Field mapperField = PostData.class.getDeclaredField("mapper");
        mapperField.setAccessible(true);
        mapperField.set(postData, mockedMapper);
    }
    @Test
    void testGet() throws IOException {
        List<Post> mockedList = new ArrayList<>();
        mockedList.add(new Post(1, "Test Title 1", "Test Body 1", new Car(), "image1.jpg", "100"));
        mockedList.add(new Post(2, "Test Title 2", "Test Body 2", new Car(), "image2.jpg", "200"));

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(mockedList);

        List<Post> result = postData.get();

        assertEquals(mockedList, result);
    }

    @Test
    void testGetById() throws IOException {
        List<Post> mockedList = new ArrayList<>();
        Post expectedPost = new Post(1, "Test Title", "Test Body", new Car(), "image.jpg", "100");
        mockedList.add(expectedPost);
        mockedList.add(new Post(2, "Another Title", "Another Body", new Car(), "image2.jpg", "200"));

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(mockedList);

        Post result = postData.get(1);

        assertEquals(expectedPost, result);
    }
    @Test
    void testCreate() throws IOException {
        List<Post> mockedList = new ArrayList<>();
        CreatePostDto newPostDto = new CreatePostDto("New Title", "New Body", new Car(), "150");

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(mockedList);
        doNothing().when(mockedMapper).writeValue(any(File.class), anyList());

        Post result = postData.create(newPostDto, "newimage.jpg");

        assertNotNull(result);
        assertEquals("New Title", result.getTitle());
    }
    @Test
    void testUpdate() throws IOException {
        List<Post> mockedList = new ArrayList<>();
        Post existingPost = new Post(1, "Old Title", "Old Body", new Car(), "oldimage.jpg", "100");
        mockedList.add(existingPost);

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(mockedList);
        doNothing().when(mockedMapper).writeValue(any(File.class), anyList());

        Post updatedPost = new Post(1, "Updated Title", "Updated Body", new Car(), "updatedimage.jpg", "150");
        postData.update(1, updatedPost);

        assertEquals("Updated Title", existingPost.getTitle());
        assertEquals("Updated Body", existingPost.getBody());
        assertEquals("updatedimage.jpg", existingPost.getImage());
    }
    @Test
    void testDelete() throws IOException {
        List<Post> mockedList = new ArrayList<>();
        Post postToDelete = new Post(1, "Title", "Body", new Car(), "image.jpg", "100");
        mockedList.add(postToDelete);

        when(mockedMapper.readValue(any(File.class), any(TypeReference.class))).thenReturn(mockedList);
        doNothing().when(mockedMapper).writeValue(any(File.class), anyList());

        postData.delete(1);

        assertTrue(mockedList.isEmpty());
    }

}

