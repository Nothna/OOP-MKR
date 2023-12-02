import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.carsharing.shared.interceptors.JwtInterceptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

class JwtInterceptorTest {
    private JwtInterceptor interceptor;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        interceptor = new JwtInterceptor();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void whenTokenIsValid_thenProceeds() throws Exception {
        String validToken = "Bearer " + createValidJwtToken();
        request.addHeader("Authorization", validToken);

        assertTrue(interceptor.preHandle(request, response, null));
    }

    @Test
    void whenTokenIsInvalid_thenThrowsException() {
        request.addHeader("Authorization", "Bearer invalid.jwt.token");

        Exception exception = assertThrows(HttpClientErrorException.class, () ->
                interceptor.preHandle(request, response, null)
        );

        assertEquals("403 Jwt token is not valid", exception.getMessage());
    }

    @Test
    void whenTokenIsMissing_thenThrowsException() {
        Exception exception = assertThrows(HttpClientErrorException.class, () ->
                interceptor.preHandle(request, response, null)
        );

        assertEquals("403 Jwt token is not valid", exception.getMessage());
    }

    private String createValidJwtToken() {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        return JWT.create()
                .withIssuer("auth0")
                .sign(algorithm);
    }
}
