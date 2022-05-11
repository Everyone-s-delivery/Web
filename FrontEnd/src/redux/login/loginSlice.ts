import { createSlice, PayloadAction } from '@reduxjs/toolkit';
import { LoginData, LoginSuccess } from '@src/model/model';

export interface AuthenticationReducerType {
  loading: boolean;
  payload?: LoginSuccess;
  error?: string;
}
const initialState: AuthenticationReducerType = {
  loading: false,
};

export const loginSlice = createSlice({
  name: 'login',
  initialState,
  reducers: {
    login: (state: AuthenticationReducerType, action: PayloadAction<LoginData>) => {
      state.loading = true;
    },
    loginFailure: (state: AuthenticationReducerType, action: PayloadAction<string>) => {
      state.loading = false;
      state.error = action.payload;
    },
    loginSuccess: (state: AuthenticationReducerType, action: PayloadAction<LoginSuccess>) => {
      state.loading = false;
      state.payload = {
        token: action.payload.token,
        userId: action.payload.userId,
      };
    },
  },
});

export const loginActions = loginSlice.actions;
export const loginReducer = loginSlice.reducer;
