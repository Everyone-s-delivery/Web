import { configureStore } from '@reduxjs/toolkit';
import { PreloadedState } from 'redux';
import { createLogger } from 'redux-logger';
import createSagaMiddleware from 'redux-saga';
import { RootState } from 'typesafe-actions';

import historyContext from './historyContext';
import rootReducer from './reducers';
import rootSaga from './sagas';

const { routerMiddleware, createReduxHistory } = historyContext;
const sagaMiddleware = createSagaMiddleware();
const logger = createLogger();
const initialState: PreloadedState<RootState> = {};

const dev = process.env.NODE_ENV === 'development';

export const store = configureStore({
  reducer: rootReducer,
  preloadedState: initialState,
  devTools: dev,
  middleware: [logger, routerMiddleware, sagaMiddleware],
});
export const history = createReduxHistory(store);
sagaMiddleware.run(rootSaga);
export default store;
