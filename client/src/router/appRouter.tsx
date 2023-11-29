import {createBrowserRouter} from "react-router-dom";
import NavBar from "../components/NavBar";
import Auth from "../pages/Auth";
import Home from "../pages/Home";

export const AppRouter = createBrowserRouter([

    {path: "/", element: <NavBar/>},
    {path: '/sign_in', element: <><NavBar/><Auth/></>},
    {path: '/sign_up', element: <><NavBar/><Auth/></>},
    {path: '/home', element: <><NavBar/><Home/></>}




]);