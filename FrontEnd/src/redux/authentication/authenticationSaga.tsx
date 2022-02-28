import { ActionType, LoginData } from '@src/model/model';
import { createApiCall, loginRoute, MethodType } from '@src/services/Api';
import { call, fork, put, takeLatest } from 'redux-saga/effects';

import { setCookie } from '../../utils/cookies';

// login
function* loginSaga({ payload }: { payload: LoginData }): Generator<any> {
  try {
    const response: any = yield call(createApiCall, {
      method: MethodType.POST,
      url: loginRoute,
      data: payload,
    });
    if (response.status === 'ok') {
      setCookie('token', response.data.token);
      yield put({
        type: ActionType.LOGIN_USER_SUCCESS,
        payload: response.data.authToken.token,
      });
    } else {
      yield put({ type: ActionType.LOGIN_USER_ERROR, payload: 'error' });
    }
  } catch (error) {
    yield put({ type: ActionType.LOGIN_USER_ERROR, payload: 'error' });
  }
}
function* onLoginSubmitWatcher() {
  yield takeLatest(ActionType.LOGIN_USER as any, loginSaga);
}

export default [fork(onLoginSubmitWatcher)];
