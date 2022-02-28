import { ActionType, SignupData } from '@src/model/model';
import { createApiCall, MethodType, signupRoute } from '@src/services/Api';
import { setCookie } from '@src/utils/cookies';
import { call, fork, put, takeLatest } from 'redux-saga/effects';

// signup
function* signupSaga({ payload }: { payload: SignupData }): Generator<any> {
  try {
    const response: any = yield call(createApiCall, {
      method: MethodType.POST,
      url: signupRoute,
      data: payload,
    });
    if (response.statusCode === 'ACCEPTED') {
      setCookie('token', response.data.authToken.token);
      yield put({
        type: ActionType.SIGNUP_SUCCESS,
        payload: '회원가입을 성공했습니다.', //response.data.authToken.token,
      });
    } else {
      yield put({ type: ActionType.SIGNUP_ERROR, payload: 'error' });
    }
  } catch (error) {
    yield put({ type: ActionType.SIGNUP_ERROR, payload: 'error' });
  }
}
function* onSignupSubmitWatcher() {
  yield takeLatest(ActionType.SIGNUP as any, signupSaga);
}

export default [fork(onSignupSubmitWatcher)];
