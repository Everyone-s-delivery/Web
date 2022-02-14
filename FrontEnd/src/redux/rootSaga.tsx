import { all } from 'redux-saga/effects';

import authenticationSaga from './authentication/authenticationSaga';
import PostsSagas from './posts/postsSaga';
import signupSaga from './signup/signupSaga';

export default function* rootSaga() {
  yield all([...PostsSagas, ...authenticationSaga, ...signupSaga]);
}
