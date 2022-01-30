import React from 'react';
import { Provider } from 'react-redux';
import { MemoryRouter as Router } from 'react-router-dom';
import { Typography } from '@mui/material';
import { render } from '@testing-library/react';
import { PersistGate } from 'redux-persist/integration/react';

import Footer from 'src/components/Footer';
import configureStore from 'src/redux/configureStore';

interface Props {
  initialEntries: string[];
  children: React.ReactNode;
}

const customRender = ({ initialEntries, children }: Props) => {
  const { persistor, store } = configureStore();
  render(
    <Provider store={store}>
      <PersistGate loading={<Typography>Loading...</Typography>} persistor={persistor}>
        <Router initialEntries={initialEntries}>
          {children}
          <Footer />
        </Router>
      </PersistGate>
    </Provider>
  );
};

export { customRender };
