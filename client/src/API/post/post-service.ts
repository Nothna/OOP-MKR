import {Post} from "../shared/Post";
import axios from "axios";

export class PostService{
    public static async getAll(): Promise<Post[]>{
        const response = await axios.get(process.env.REACT_APP_SERVER_URL + "/posts");
        return response.data;

    }
}