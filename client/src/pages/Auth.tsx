import React, {FormEvent, useContext, useState} from 'react';
import {Card, Container, Form, FormControl, Button, Row, Col} from "react-bootstrap";
import {Context} from "../index";
import {NavLink, useLocation, useNavigate} from "react-router-dom";
import {UserService} from "../API/user/user-service";
import {omit} from "lodash";

const Auth = () => {

    const [formData, setFormData] = useState({
        nickname: '',
        email: '',
        password: '',
    });

    const location = useLocation();
    const endPoint = location.pathname;
    const {userStore} = useContext(Context);
    const router = useNavigate();
    let accessToken: string;

    const pushData = async (event: FormEvent) =>{
        event.preventDefault();


        switch(endPoint){
            case "/sign_in":
                accessToken = await UserService.signIn(omit(formData, "nickname"));
                userStore.setIsAuth(true);
                localStorage.setItem("accessToken", accessToken);
                router("/home")
                break;
            case '/sign_up':
                accessToken = await UserService.signIn(formData);
                userStore.setIsAuth(true);
                localStorage.setItem("accessToken", accessToken);
                router("/home")
                break;
        }

    }

    return (
        <Container
            className="d-flex justify-content-center align-items-center"
            style={{height: window.innerHeight - 54}}
        >
            <Card
                className="p-4"
                style={{width: 400, background:'lightgrey'}}

            >
                {endPoint === '/sign_in' ?
                    <h2 className="m-auto">Authorization</h2>
                    : <h2 className="m-auto">Registration</h2>
                }

                <Form className="d-flex flex-column"  onSubmit={pushData}>
                    {endPoint === '/sign_up' ? <FormControl className="mt-4"
                                                            placeholder="Name..."
                                                            type="text"
                                                            onChange={(event) => setFormData({...formData, nickname: event.target.value})}
                        />
                        : <></>
                    }
                    <FormControl className="mt-3"
                                 placeholder="Email..."
                                 type="email"
                                 onChange={(event) => setFormData({...formData, email: event.target.value})}
                    />
                    <FormControl className="mt-3"
                                 placeholder="Password..."
                                 type="password"
                                 onChange={(event) => setFormData({...formData, password: event.target.value})}
                    />
                    <Row className="m-0" style={{paddingTop:10}}>
                        <Col>
                            {endPoint === '/sign_in' ? (
                                <div>
                                    <NavLink style={{ color: 'darkcyan' }} to={"/sign_up"}>
                                        Don't have an account? Sign Up!
                                    </NavLink>
                                </div>
                            ) : (

                                <div>
                                    <NavLink style={{ color: 'darkcyan' }} to={"/sign_in"}>
                                        Already registered? Sign In!
                                    </NavLink>
                                </div>

                            )}
                        </Col>
                        <Col className="d-flex justify-content-end m-lg-auto">
                            <Button type={"submit"}>Submit</Button>
                        </Col>
                    </Row>


                </Form>
            </Card>
        </Container>
    );
};

export default Auth;