import React from 'react';
import {Card, Container} from "react-bootstrap";

const About = () => {
    return (
        <Container className="d-flex align-items-center mt-5">
            <Card>
                <h3>
                    Hello! This is a carsharing service project for MKR.
                    Click on the big label at the left top corner to start using it.
                </h3>
            </Card>
        </Container>

    );
};

export default About;