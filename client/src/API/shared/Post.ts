import {Car} from "./Car";

export interface Post{
    id: number;
    title: string;
    image: string;
    body: string;
    price: string;
    car: Car;
}