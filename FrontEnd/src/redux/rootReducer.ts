import { routerReducer, RouterState } from 'react-router-redux';
import { History } from 'history';
import { combineReducers } from 'redux';

import { listingReducer, ListingReducerType } from '../redux/listing/listingReducer';
import {
  authenticationReducer,
  AuthenticationReducerType,
} from './authentication/authenticationReducer';
import { signupReducer, SignupReducerType } from './signup/signupReducer';

export interface RootState {
  listing: ListingReducerType;
  loginForm: AuthenticationReducerType;
  signup: SignupReducerType;
  routerReducer: RouterState;
}

export default (history: History) =>
  combineReducers({
    listing: listingReducer,
    loginForm: authenticationReducer,
    signup: signupReducer,
    routerReducer,
  });
