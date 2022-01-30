import { call, fork, put, takeLatest } from 'redux-saga/effects';

import { ActionType } from 'src/model/model';
import { createApiCall, listingRoute, MethodType } from '../../services/Api';

// login
function* getListingSaga({ payload }: { payload?: string }): Generator<any> {
  try {
    const response: any = yield call(createApiCall, {
      method: MethodType.GET,
      url: `${listingRoute}${payload ? `?timestamp=${payload}` : ''}`,
      data: undefined,
      auth: true,
    });
    if (response.status === 'ok') {
      yield put({
        type: ActionType.LISTING_REQUEST_SUCCESS,
        payload: response.data.saleListings,
      });
    } else {
      yield put({ type: ActionType.LOGIN_USER_ERROR, payload: 'error' });
    }
  } catch (error) {
    yield put({ type: ActionType.LISTING_REQUEST_ERROR, payload: error });
  }
}
function* onLoginSubmitWatcher() {
  yield takeLatest(ActionType.LISTING_REQUEST as any, getListingSaga);
}

export default [fork(onLoginSubmitWatcher)];
