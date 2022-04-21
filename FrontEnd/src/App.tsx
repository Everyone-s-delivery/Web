import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';

import { GlobalStyle, theme } from './App.style';
import Footer from './components/Footer';
import LoginScreen from './screens/LoginScreen/LoginScreen';
import Posts from './screens/Posts/Posts';
import SignupScreen from './screens/Signup/Signup';

const App = () => {
  return (
    <ThemeProvider theme={theme}>
      <GlobalStyle />
      <BrowserRouter basename="/Web">
        <Routes>
          <Route path="/" element={<LoginScreen />}></Route>
          <Route path="/login" element={<LoginScreen />}></Route>
          <Route path="/posts" element={<Posts />}></Route>
          <Route path="/signup" element={<SignupScreen />}></Route>
        </Routes>
        <Footer />
      </BrowserRouter>
    </ThemeProvider>
  );
};

export default App;
