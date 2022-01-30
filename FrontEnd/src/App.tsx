import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import Footer from './components/Footer';
import LoginScreen from './screens/LoginScreen';
import SignupScreen from './screens/Signup';
import withRoot from './withRoot';

import './App.css';

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginScreen />}></Route>
        <Route path="/login" element={<LoginScreen />}></Route>
        <Route path="/signup" element={<SignupScreen />}></Route>
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default withRoot(App);
