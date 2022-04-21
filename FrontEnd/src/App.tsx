import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';

import { GlobalStyle, theme } from './App.style';
import Login from './pages/Login/Login';
import Posts from './pages/Posts';
import SignUp from './pages/SignUp/SignUp';

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <BrowserRouter basename="/Web">
        <Routes>
          <Route index element={<Login />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="posts/*" element={<Posts />} />
          <Route path="/signup" element={<SignUp />}></Route>
        </Routes>
        {/* <Footer /> */}
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
