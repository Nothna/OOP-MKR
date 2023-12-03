import com.example.carsharing.shared.Car;
import com.example.carsharing.shared.Rental;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;
import com.example.carsharing.Posts.Post;


class CarRentalPostTest {

    @Test
    void testCarCreationAndGetters() {
        Car car = new Car("KIA", "Cee`d", 2017, 4, "4x2");

        assertEquals("KIA", car.getManufacturer());
        assertEquals("Cee`d", car.getModel());
        assertEquals(2017, car.getYear());
        assertEquals(4, car.getSeats());
        assertEquals("4x2", car.getWheelBase());
    }


    @Test
    void testRentalCreationAndGetters() {
        Post post = new Post(); // Create a Post object
        LocalDateTime startTime = LocalDateTime.now();
        LocalDateTime endTime = LocalDateTime.now().plusDays(1);

        Rental rental = new Rental(post, startTime, endTime); // Use the Post object

        assertEquals(post, rental.getPost());
        assertEquals(startTime, rental.getStartTime());
        assertEquals(endTime, rental.getEndTime());
    }

    @Test
    void testPostCreationAndGetters() {
        Car car = new Car();
        Post post = new Post(1, "Title", "Body", car, "Image", "Price");

        assertEquals(1, post.getId());
        assertEquals("Title", post.getTitle());
        assertEquals("Body", post.getBody());
        assertEquals(car, post.getCar());
        assertEquals("Image", post.getImage());
        assertEquals("Price", post.getPrice());
    }
}
