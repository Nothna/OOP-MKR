import {SignInUserDto} from "./dto/sign-in-user.dto";
import axios, {AxiosResponse} from "axios";
import {SignUpUserDto} from "./dto/sign-up-user.dto";

export class UserService{
    public static async signIn(signInUserDto: SignInUserDto): Promise<string>{
        const response = await axios.post(process.env.REACT_APP_SERVER_URL + "/auth/sign_in", signInUserDto);
        const accessToken = response.data;
        return accessToken;
    }

    public static async signUp(signUpUserDto: SignUpUserDto): Promise<string>{
        const response = await axios.post(process.env.REACT_APP_SERVER_URL + "/auth/sign_up", signUpUserDto);
        const accessToken = response.data;
        return accessToken;
    }

    public static async order(postId: number, hours: number, accessToken: string): Promise<AxiosResponse>{
        const response = await axios.post(process.env.REACT_APP_SERVER_URL + "/users/order", {postId: postId, hours: hours});
        return response
    }

}