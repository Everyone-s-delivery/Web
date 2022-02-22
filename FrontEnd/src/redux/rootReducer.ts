import { routerReducer, RouterState } from 'react-router-redux';
import { combineReducers } from 'redux';

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import {
  authenticationReducer,
  AuthenticationReducerType,
} from './authentication/authenticationReducer';
=======
import { AuthenticationReducerType } from './login/authenticationReducer';
import { loginReducer } from './login/loginSlice';
>>>>>>> dev
=======
import { AuthenticationReducerType } from './login/authenticationReducer';
import { loginReducer } from './login/loginSlice';
>>>>>>> dev
=======
import { AuthenticationReducerType } from './login/authenticationReducer';
import { loginReducer } from './login/loginSlice';
>>>>>>> dev
=======
import { AuthenticationReducerType } from './login/authenticationReducer';
import { loginReducer } from './login/loginSlice';
>>>>>>> dev
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
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
  loginForm: authenticationReducer,
=======
  loginForm: loginReducer,
>>>>>>> dev
=======
  loginForm: loginReducer,
>>>>>>> dev
=======
  loginForm: loginReducer,
>>>>>>> dev
=======
  loginForm: loginReducer,
>>>>>>> dev
  signup: signupReducer,
  routerReducer,
});

export default rootReducer;
