import { all } from 'redux-saga/effects';

import authenticationSaga from './login/loginSaga';
import PostsSagas from './posts/postsSaga';
import signupSaga from './signup/signupSaga';

export default function* rootSaga() {
  yield all([...PostsSagas, ...authenticationSaga, ...signupSaga]);
}
