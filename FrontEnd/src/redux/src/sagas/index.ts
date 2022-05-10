import { all } from 'redux-saga/effects';

import loginSaga from './src/login.saga';

function* rootSaga() {
  yield all([...loginSaga]);
}

export default rootSaga;
