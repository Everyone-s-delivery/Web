import { combineReducers } from 'redux';

import historyContext from '../historyContext';
import loginReducer from './src/login.reducer';

const rootReducer = combineReducers({
  router: historyContext.routerReducer,
  loginReducer,
});

export default rootReducer;
