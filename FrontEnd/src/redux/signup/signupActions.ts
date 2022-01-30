import { ActionType, SignupData } from 'src/model/model';

export const signupAction = (payload: SignupData) => {
  return {
    type: ActionType.SIGNUP,
    payload,
  };
};
