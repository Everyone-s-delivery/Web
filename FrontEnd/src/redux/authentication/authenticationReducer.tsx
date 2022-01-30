import { Action, ActionType, LoginData } from 'src/model/model';
import createReducer from '../createReducer';

export interface AuthenticationReducerType extends LoginData {
  loading: boolean;
  error?: string;
  token: string;
}
const defaultState: AuthenticationReducerType = {
  email: '',
  password: '',
  loading: false,
  error: undefined,
  token: '',
};

export const authenticationReducer = createReducer<AuthenticationReducerType>(
  defaultState,
  {
    [ActionType.LOGIN_USER](
      state: AuthenticationReducerType,
      action: Action<LoginData>
    ) {
      return {
        ...state,
        loading: true,
      };
    },

    [ActionType.LOGIN_USER_ERROR](
      state: AuthenticationReducerType,
      action: Action<number>
    ) {
      return {
        ...state,
        loading: false,
        error: action.payload,
      };
    },

    [ActionType.LOGIN_USER_SUCCESS](
      state: AuthenticationReducerType,
      action: Action<number>
    ) {
      return {
        ...state,
        loading: false,
        error: null,
        token: action.payload,
      };
    },
  }
);
