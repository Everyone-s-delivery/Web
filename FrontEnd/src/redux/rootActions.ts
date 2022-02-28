import * as AuthenticationActions from 'src/redux/authentication/authenticationActions';
import * as postsActions from 'src/redux/posts/postsActions';
import * as signupActions from 'src/redux/signup/signupActions';

export const ActionCreators = Object.assign(
  {},
  { ...postsActions, ...AuthenticationActions, ...signupActions }
);
