import {SignInUserDto} from "./dto/sign-in-user.dto";
import axios from "axios";
import {SignUpUserDto} from "./dto/sign-up-user.dto";

export class UserService{
    public static async signIn(signInUserDto: SignInUserDto): Promise<string>{
        const response = await axios.post(process.env.REACT_APP_SERVER_URL + "/api/auth/sign_in", signInUserDto);
        const accessToken = response.data;
        return accessToken;
    }

    public static async signUp(signUpUserDto: SignUpUserDto): Promise<string>{
        const response = await axios.post(process.env.REACT_APP_SERVER_URL + "/api/auth/sign_up", signUpUserDto);
        const accessToken = response.data;
        return accessToken;
    }
}