import * as React from 'react';
import { QueryClient, QueryClientProvider } from 'react-query';
import { Provider } from 'react-redux';
import Typography from '@mui/material/Typography';
import { PersistGate } from 'redux-persist/integration/react';

import App from '../App';
import configureStore from './configureStore';

const { persistor, store } = configureStore();

const queryClient = new QueryClient();
function ReduxRoot() {
  return (
    <Provider store={store}>
      <QueryClientProvider client={queryClient}>
        <PersistGate loading={<Typography>Loading...</Typography>} persistor={persistor}>
          <App />
        </PersistGate>
      </QueryClientProvider>
    </Provider>
  );
}

export { store };

export default ReduxRoot;
