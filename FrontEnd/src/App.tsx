import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Global, ThemeProvider } from '@emotion/react';

import { globalStyle } from './components/@styled/globalStyle';
import { theme } from './constants/theme';
import Login from './pages/Login/Login';
import Posts from './pages/Posts';
import SignUp from './pages/SignUp/SignUp';

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <Global styles={globalStyle} />
      <Routes>
        <Route index element={<Login />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="posts/*" element={<Posts />} />
        <Route path="/signup" element={<SignUp />}></Route>
      </Routes>
    </ThemeProvider>
  );
};

export default App;
