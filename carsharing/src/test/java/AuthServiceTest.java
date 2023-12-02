import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.carsharing.Auth.AuthService;
import com.example.carsharing.Auth.UserJwtPayload;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AuthServiceTest {
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

        assertEquals(123, payload.id);
        assertEquals("test@example.com", payload.email);
    }

    @Test
    void testVerifyUserWithInvalidToken() {
        String token = "invalidToken";
        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }
    @Test
    void testVerifyUserWithTokenMissingRequiredClaims() throws Exception {
        String token = JWT.create().sign(algorithm);
        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }

    @Test
    void testVerifyUserWithInvalidDataTypes() throws Exception {
        String token = JWT.create()
                .withClaim("id", "invalidId")
                .withClaim("email", 123)
                .sign(algorithm);

        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }
    @Test
    void testVerifyUserWithEmptyClaims() throws Exception {
        String token = JWT.create()
                .withClaim("id", "")
                .withClaim("email", "")
                .sign(algorithm);

        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }
    @Test
    void testVerifyUserWithIncorrectSecret() throws Exception {
        // Create a token with a different secret
        Algorithm wrongAlgorithm = Algorithm.HMAC256("wrongSecret");
        String token = JWT.create()
                .withClaim("id", 123)
                .withClaim("email", "test@example.com")
                .sign(wrongAlgorithm);

        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }

    @Test
    void testVerifyUserWithAdditionalClaims() throws Exception {
        String token = JWT.create()
                .withClaim("id", 123)
                .withClaim("email", "test@example.com")
                .withClaim("extra", "extraData")
                .sign(algorithm);

        UserJwtPayload payload = authService.verifyUser(token);

        assertEquals(123, payload.id);
        assertEquals("test@example.com", payload.email);
    }

    @Test
    void testVerifyUserWithExpiredToken() throws Exception {
        String token = JWT.create()
                .withClaim("id", 123)
                .withClaim("email", "test@example.com")
                .withExpiresAt(new Date(System.currentTimeMillis() - 10000)) // expired 10 seconds ago
                .sign(algorithm);

        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }

    @Test
    void testVerifyUserWithTokenSignedWithDifferentSecret() throws Exception {
        Algorithm differentAlgorithm = Algorithm.HMAC256("differentSecret");
        String token = JWT.create()
                .withClaim("id", 123)
                .withClaim("email", "test@example.com")
                .sign(differentAlgorithm);

        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }

    @Test
    void testVerifyUserWithMalformedToken() {
        String token = "this.is.not.a.valid.token";
        assertThrows(Exception.class, () -> authService.verifyUser(token));
    }


}
