import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.carsharing.shared.dto.UserJwtPayload;

class UserJwtPayloadTest {

    @Test
    void testUserJwtPayload() {
        long expectedId = 123;
        String expectedEmail = "test@example.com";

        UserJwtPayload payload = new UserJwtPayload(expectedId, expectedEmail);

        assertEquals(expectedId, payload.getId(), "The id should match the constructor argument.");
        assertEquals(expectedEmail, payload.getEmail(), "The email should match the constructor argument.");
    }
}
