import React from 'react';
import {RouterProvider} from "react-router-dom";
import {AppRouter} from "./router/appRouter";

function App() {
  return (
    <RouterProvider
        router={AppRouter}
    />
  );
}

export default App;
