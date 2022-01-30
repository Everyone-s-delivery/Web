import { ActionType, LoginData } from 'src/model/model';

export const loginUserAction = (payload: LoginData) => {
  return {
    type: ActionType.LOGIN_USER,
    payload,
  };
};
