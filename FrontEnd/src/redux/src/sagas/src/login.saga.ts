import { LOCAL_STORAGE_KEY } from '@src/constants';
import API from '@src/services/requests';
import { setLocalStorageItem } from '@src/utils/localStorage';
import { push } from 'redux-first-history';
import { call, fork, put, takeLatest } from 'redux-saga/effects';

import Actions from '../../actions';

// login
function* loginSaga({ payload }: { payload: LoginRequestBody }): Generator {
  const { fetchLogin } = Actions.loginActions;

  try {
    const response: any = yield call(API.login, payload);

    if (response.token && response.userId) {
      yield put(
        fetchLogin.success({
          token: response.token,
          userId: response.userId,
        })
      );
      setLocalStorageItem(LOCAL_STORAGE_KEY.ACCESS_TOKEN, response.token);
    } else {
      yield put(fetchLogin.failure({ message: '인증 실패', status: 401 }));
    }
  } catch (error) {
    yield put(fetchLogin.failure({ message: JSON.stringify(error), status: 500 }));
  }
}

function* fetchLoginSuccessSaga({ payload }: { payload: LoginResponseBody }) {
  const { userId } = payload;
  yield alert(`${userId}님 로그인하셨습니다.`);
  yield put(push('/posts'));
}
function* fetchLoginFailureSaga({ payload }: { payload: RequestValidationError }) {
  const { message } = payload;
  yield alert(message);
  yield put(push('/login'));
}

function* onFetchRequestWatcher() {
  const { fetchLogin } = Actions.loginActions;
  yield takeLatest(fetchLogin.request, loginSaga);
}

function* onFetchSuccessWatcher() {
  const { fetchLogin } = Actions.loginActions;
  yield takeLatest(fetchLogin.success, fetchLoginSuccessSaga);
}

function* onFetchFailureWatcher() {
  const { fetchLogin } = Actions.loginActions;
  yield takeLatest(fetchLogin.failure, fetchLoginFailureSaga);
}

export default [
  fork(onFetchRequestWatcher),
  fork(onFetchSuccessWatcher),
  fork(onFetchFailureWatcher),
];
