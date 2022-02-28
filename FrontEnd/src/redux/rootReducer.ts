import { routerReducer, RouterState } from 'react-router-redux';
import { combineReducers } from 'redux';

import { AuthenticationReducerType, loginReducer } from './login/loginSlice';
import { postsReducer, PostsReducerType } from './posts/postsReducer';
import { signupReducer, SignupReducerType } from './signup/signupReducer';

export interface RootState {
  posts: PostsReducerType;
  loginForm: AuthenticationReducerType;
  signup: SignupReducerType;
  routerReducer: RouterState;
}
const rootReducer = combineReducers({
  posts: postsReducer,
  loginForm: loginReducer,
  signup: signupReducer,
  routerReducer,
});

export default rootReducer;
