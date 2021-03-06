import { LOCAL_STORAGE_KEY } from '@src/constants';
import { LoginData } from '@src/model/model';
import { createApiCall, loginRoute, MethodType } from '@src/services/Api';
import { setLocalStorageItem } from '@src/utils/localStorage';
import { call, fork, put, takeLatest } from 'redux-saga/effects';

import { setCookie } from '../../utils/cookies';
import { loginActions } from './loginSlice';

// login
function* loginSaga({ payload }: { payload: LoginData }): Generator<any> {
  const { loginSuccess, loginFailure } = loginActions;

  try {
    const response: any = yield call(createApiCall, {
      method: MethodType.POST,
      url: loginRoute,
      data: payload,
    });
    if (response.token && response.userId) {
      setCookie('token', response.token);

      yield put(
        loginSuccess({
          token: response.token,
          userId: response.userId,
        })
      );
      setLocalStorageItem(LOCAL_STORAGE_KEY.ACCESS_TOKEN, response.token);
    } else {
      yield put(loginFailure(response.errorMsg));
    }
  } catch (error) {
    yield put(loginFailure('error'));
  }
}
function* onLoginSubmitWatcher() {
  yield takeLatest(loginActions.login as any, loginSaga);
}

export default [fork(onLoginSubmitWatcher)];
