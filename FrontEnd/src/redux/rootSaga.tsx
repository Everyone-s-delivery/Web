import { all } from 'redux-saga/effects';

import listinSagas from '../redux/listing/listingSaga';
import authenticationSaga from './authentication/authenticationSaga';
import signupSaga from './signup/signupSaga';

export default function* rootSaga() {
  yield all([...listinSagas, ...authenticationSaga, ...signupSaga]);
}
