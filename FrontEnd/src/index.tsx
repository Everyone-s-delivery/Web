import React from 'react';
import ReactDOM from 'react-dom';
import { QueryClient, QueryClientProvider } from 'react-query';
import { Provider } from 'react-redux';
import { HistoryRouter as Router } from 'redux-first-history/rr6';

import App from './App';
import serviceWorker from './mocks/browser';
import { history, Store } from './redux';
import reportWebVitals from './reportWebVitals';

import './index.css';

if (process.env.NODE_ENV === 'development') {
  serviceWorker.start({ onUnhandledRequest: 'bypass' });
}
const queryClient = new QueryClient();
ReactDOM.render(
  <Provider store={Store}>
    <Router history={history}>
      <QueryClientProvider client={queryClient}>
        <App />
      </QueryClientProvider>
    </Router>
  </Provider>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
