import React, {createContext} from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import UserStore from "./store/UserStore";
import {AppRouter} from "./router/appRouter";
import {RouterProvider} from "react-router-dom";

export const Context = createContext({userStore: new UserStore()});

const root = ReactDOM.createRoot(
    document.getElementById('root') as HTMLElement
);
root.render(
    <React.StrictMode>
        <Context.Provider
            value={{
                userStore: new UserStore()
            }}
        >
            <RouterProvider
                router={AppRouter}
            />
        </Context.Provider>


    </React.StrictMode>
);

