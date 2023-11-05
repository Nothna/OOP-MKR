import {BrowserRouter, createBrowserRouter} from "react-router-dom";
import NavBar from "./Components/NavBar";
import Home from "./pages/Home";
import About from "./pages/About";

export const AppRouter = createBrowserRouter([
    {path:'/', element:<><NavBar/><About/></>},
    {path:'home', element:<><NavBar/><Home/></>}
])