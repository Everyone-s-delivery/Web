import { all } from 'redux-saga/effects';

<<<<<<< HEAD
import authenticationSaga from './login/loginSaga';
=======
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import authenticationSaga from './authentication/authenticationSaga';
=======
import authenticationSaga from './login/loginSaga';
>>>>>>> dev
=======
import authenticationSaga from './login/loginSaga';
>>>>>>> dev
=======
import authenticationSaga from './login/loginSaga';
>>>>>>> dev
=======
import authenticationSaga from './login/loginSaga';
>>>>>>> dev
>>>>>>> 7f319b30
import PostsSagas from './posts/postsSaga';
import signupSaga from './signup/signupSaga';

export default function* rootSaga() {
  yield all([...PostsSagas, ...authenticationSaga, ...signupSaga]);
}
