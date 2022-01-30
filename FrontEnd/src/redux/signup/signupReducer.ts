import { Action, ActionType, SignupData } from 'src/model/model';
import createReducer from '../createReducer';

export interface SignupReducerType extends SignupData {
  loading: boolean;
  error?: string;
  message: string;
}
const defaultState: SignupReducerType = {
  email: '',
  nickName: '',
  password: '',
  address: '',
  loading: false,
  error: undefined,
  message: '',
};

export const signupReducer = createReducer<SignupReducerType>(defaultState, {
  [ActionType.SIGNUP](state: SignupReducerType, action: Action<SignupData>) {
    return {
      ...state,
      loading: true,
    };
  },

  [ActionType.SIGNUP_ERROR](state: SignupReducerType, action: Action<number>) {
    return {
      ...state,
      loading: false,
      error: action.payload,
    };
  },

  [ActionType.SIGNUP_SUCCESS](
    state: SignupReducerType,
    action: Action<string>
  ) {
    return {
      ...state,
      loading: false,
      error: null,
      message: action.payload,
    };
  },
});
