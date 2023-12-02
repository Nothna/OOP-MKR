import {createBrowserRouter} from "react-router-dom";
import NavBar from "../components/NavBar";
import Auth from "../pages/Auth";
import Home from "../pages/Home";
import ItemDetail from "../pages/ItemDetail";



/*
export const AppRouter = createBrowserRouter([

    {path: "/", element: <NavBar/>, children:[
            {
                path: "sign_in", element: <Auth/>
            },
            {
                path: "sign_up", element: <Auth/>
            },
            {
                path: "home", element: <Home/>
            },
            {
                path: "item/:id", element: <ItemDetail/>
            },
        ]}




]);*/

export const AppRouter = createBrowserRouter([
    {path: "/", element: <NavBar/>},
    {path: "/sign_in", element: <><NavBar/><Auth/></>},
    {path: "/sign_up", element: <><NavBar/><Auth/></>},
    {path: "/home", element:<><NavBar/><Home/></>},
    {path: "/item/:id", element:<><NavBar/><ItemDetail/></>},


])
