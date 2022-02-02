import { call, fork, put, takeLatest } from 'redux-saga/effects';

import { ActionType } from 'src/model/model';
import { usePostsQuery } from 'src/services/queries/posts';
import { createApiCall, listingRoute, MethodType } from '../../services/Api';

// login
function* getListingSaga(): Generator<any> {
  try {
    const response: any = yield call(usePostsQuery);
    if (response.status === 'ok') {
      yield put({
        type: ActionType.LISTING_REQUEST_SUCCESS,
        payload: response,
      });
    } else {
      yield put({ type: ActionType.LISTING_REQUEST_ERROR, payload: response.status });
    }
  } catch (error) {
    yield put({ type: ActionType.LISTING_REQUEST_ERROR, payload: error });
  }
}
function* onLoginSubmitWatcher() {
  yield takeLatest(ActionType.LISTING_REQUEST as any, getListingSaga);
}

export default [fork(onLoginSubmitWatcher)];
