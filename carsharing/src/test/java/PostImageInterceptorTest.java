import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.web.client.HttpClientErrorException;
import com.example.carsharing.shared.interceptors.PostImageInterceptor;
import org.springframework.web.client.HttpServerErrorException;


import static org.junit.jupiter.api.Assertions.*;

class PostImageInterceptorTest {
    private PostImageInterceptor interceptor;
    private MockMultipartHttpServletRequest multipartRequest;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        interceptor = new PostImageInterceptor();
        multipartRequest = new MockMultipartHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void whenImageIsUploadedSuccessfully_thenAttributeIsSet() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile("image", "filename.jpg", "image/jpeg", "test image content".getBytes());
        multipartRequest.addFile(mockFile);

        assertTrue(interceptor.preHandle(multipartRequest, response, null));
        assertNotNull(multipartRequest.getAttribute("image"));
    }

    @Test
    void whenNoImageIsProvided_thenThrowsException() {
        MockHttpServletRequest request = new MockHttpServletRequest();

        Exception exception = assertThrows(HttpClientErrorException.class, () ->
                interceptor.preHandle(request, response, null)
        );

        assertEquals("400 No image provided", exception.getMessage());
    }

    @Test
    void whenImageHasInvalidExtension_thenThrowsException() {
        MockMultipartFile mockFile = new MockMultipartFile("image", "filename.txt", "text/plain", "test text content".getBytes());
        multipartRequest.addFile(mockFile);

        Exception exception = assertThrows(HttpServerErrorException.class, () ->
                interceptor.preHandle(multipartRequest, response, null)
        );

        assertEquals("500 Internal server error", exception.getMessage());
    }

}
