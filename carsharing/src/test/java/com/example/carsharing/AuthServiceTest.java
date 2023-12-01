package com.example.carsharing;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.example.carsharing.auth.AuthService;
import com.example.carsharing.auth.UserJwtPayload;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    private AuthService authService;
    private Algorithm algorithm;

    @BeforeEach
    void setUp() throws Exception {
        algorithm = Algorithm.HMAC256("Somesecretkeyherenothingweryinterestingthough");
        authService = new AuthService();
    }

    @Test
    void testVerifyUserWithValidToken() throws Exception {
        String token = JWT.create()
                .withClaim("id", 123)
                .withClaim("email", "test@example.com")
                .sign(algorithm);

        UserJwtPayload payload = authService.verifyUser(token);

        assertEquals(123, payload.id); // Accessing the public field directly
        assertEquals("test@example.com", payload.email); // Accessing the public field directly
    }


    @Test
    void testVerifyUserWithInvalidToken() {
        String token = "invalidToken";
        assertThrows(Exception.class, () -> {
            authService.verifyUser(token);
        });
    }

    // інші тести...
}
