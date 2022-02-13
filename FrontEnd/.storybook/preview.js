import { BrowserRouter as Router } from 'react-router-dom';
import { Provider } from 'react-redux';
import configureStore from '@src/redux/configureStore';
import { PersistGate } from 'redux-persist/integration/react';
import { ThemeProvider } from 'styled-components';
import { GlobalStyle, theme } from '@src/App.style';
const { persistor, store } = configureStore();

export const parameters = {
  actions: { argTypesRegex: "^on[A-Z].*" },
  controls: {
    matchers: {
      color: /(background|color)$/i,
      date: /Date$/,
    },
  },
};

export const decorators = [
  (Story) => (
  <Provider store={store}>
    <PersistGate loading={<span>Loading...</span>} persistor={persistor}>
      <ThemeProvider theme={theme}>
        <GlobalStyle />
        <Router>
          <Story />
        </Router>
      </ThemeProvider>
    </PersistGate>
  </Provider>
  ),
];