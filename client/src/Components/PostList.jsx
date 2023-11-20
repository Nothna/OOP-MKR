
import React from 'react';
import { Card, Container, Row, Col, Button } from 'react-bootstrap';

const cars = [
    { id: 1, name: 'Toyota Corolla', image: 'car1.jpg', description: 'Comfortable and reliable family car.' },
    { id: 2, name: 'Honda Civic', image: 'car2.jpg', description: 'Sleek and sporty sedan for everyday use.' },
    { id: 3, name: 'Tesla Model 3', image: 'car3.jpg', description: 'Electric car with futuristic features and design.' },

];

const PostList = () => {
    return (
        <Container className="my-5">
            <h1>Welcome to our Car Sharing Service</h1>
            <Row xs={1} md={2} lg={3} className="g-4 my-5">
                {cars.map((car) => (
                    <Col key={car.id}>
                        <Card>
                            <Card.Img variant="top" src={car.image} />
                            <Card.Body>
                                <Card.Title>{car.name}</Card.Title>
                                <Card.Text>{car.description}</Card.Text>
                                <Button variant="primary">Order Now</Button>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>
        </Container>
    );
};

export default PostList();
