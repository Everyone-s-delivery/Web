import { ActionType, createReducer } from 'typesafe-actions';

import Actions from '../../actions';

export interface LoginStore {
  loading: boolean;
  error?: string;
  token?: string;
}

const initialState: LoginStore = {
  loading: false,
  error: '',
};

// eslint-disable-next-line max-len
const loginReducer = createReducer<LoginStore, ActionType<typeof Actions.loginActions>>(
  initialState
)
  .handleAction(Actions.loginActions.fetchLogin.request, () => ({
    loading: true,
  }))
  .handleAction(Actions.loginActions.fetchLogin.success, (state, { payload }) => ({
    ...state,
    token: payload.token,
    loading: false,
  }))
  .handleAction(Actions.loginActions.fetchLogin.failure, (state, { payload }) => ({
    ...state,
    error: payload.message,
    token: undefined,
    loading: false,
  }));

export default loginReducer;
