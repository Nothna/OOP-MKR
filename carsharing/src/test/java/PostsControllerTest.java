import com.example.carsharing.Posts.Post;
import com.example.carsharing.Posts.PostsController;
import com.example.carsharing.Posts.dto.CreatePostDto;
import com.example.carsharing.shared.Car;
import com.example.carsharing.shared.dataWriter.PostData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class PostsControllerTest {

    @Mock
    private PostData postData;

    private PostsController postsController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        try {
            postsController = new PostsController();
            ReflectionTestUtils.setField(postsController, "postData", postData);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Exception during setup: " + e.getMessage());
        }
    }


    @Test
    void getByIdTest_PostNotFound() throws IOException {
        long id = 1L;
        when(postData.get(id)).thenReturn(null);

        assertThrows(HttpClientErrorException.class, () -> postsController.getById(id));
    }


    @Test
    void getByIdTest_PostExists() throws Exception {
        long id = 1L;
        Post mockPost = new Post(id, "Title", "Body", new Car(), "image.jpg", "100");
        when(postData.get(id)).thenReturn(mockPost);

        ResponseEntity<Post> response = postsController.getById(id);

        assertEquals(200, response.getStatusCodeValue());
        Post resultPost = response.getBody();
        assertNotNull(resultPost);
        assertEquals(mockPost.getTitle(), resultPost.getTitle());
    }

    @Test
    void getAllTest() throws Exception {
        List<Post> mockPosts = Arrays.asList(
                new Post(1L, "Title1", "Body1", new Car(), "image1.jpg", "100"),
                new Post(2L, "Title2", "Body2", new Car(), "image2.jpg", "200")
        );
        when(postData.get()).thenReturn(mockPosts);

        ResponseEntity<List<Post>> response = postsController.getAll();

        assertEquals(200, response.getStatusCodeValue());
        List<Post> resultPosts = response.getBody();
        assertNotNull(resultPosts);
        assertEquals(mockPosts.size(), resultPosts.size());
    }

    @Test
    void createTest() throws Exception {
        CreatePostDto createPostDto = new CreatePostDto("Title", "Body", new Car(), "100");
        Post mockPost = new Post(1L, "Title", "Body", new Car(), "image.jpg", "100");
        when(postData.create(createPostDto, "image.jpg")).thenReturn(mockPost);

        ResponseEntity<Post> response = postsController.create(createPostDto, "image.jpg");

        assertEquals(200, response.getStatusCodeValue());
        Post resultPost = response.getBody();
        assertNotNull(resultPost);
        assertEquals(mockPost.getTitle(), resultPost.getTitle());
    }
}
