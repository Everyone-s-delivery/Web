import { routerReducer, RouterState } from 'react-router-redux';
import { History } from 'history';
import { combineReducers } from 'redux';

import {
  authenticationReducer,
  AuthenticationReducerType,
} from './authentication/authenticationReducer';
import { listingReducer, PostsReducerType } from './posts/postsReducer';
import { signupReducer, SignupReducerType } from './signup/signupReducer';

export interface RootState {
  posts: PostsReducerType;
  loginForm: AuthenticationReducerType;
  signup: SignupReducerType;
  routerReducer: RouterState;
}
const rootReducer = combineReducers({
  posts: listingReducer,
  loginForm: authenticationReducer,
  signup: signupReducer,
  routerReducer,
});

export default rootReducer;
