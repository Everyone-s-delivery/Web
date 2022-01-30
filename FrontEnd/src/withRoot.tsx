import React from 'react';
import { CssBaseline } from '@mui/material';
import { createTheme, ThemeProvider } from '@mui/material/styles';

const theme = createTheme({
  palette: {
    primary: {
      light: '#e5e5e5',
      main: '#727272',
      dark: '#363839',
      contrastText: '#fff',
    },
    secondary: {
      light: '#ff5e50',
      main: '#e41e26',
      dark: '#a90000',
      contrastText: '#fff',
    },
  },
});

const withRoot = (Component: any) => {
  const WithRoot = (props: object) => {
    return (
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Component {...props} />
      </ThemeProvider>
    );
  };

  return WithRoot;
};

export default withRoot;
