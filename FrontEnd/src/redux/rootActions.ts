import * as AuthenticationActions from 'src/redux/authentication/authenticationActions';
import * as ListingActions from 'src/redux/listing/listingActions';
import * as signupActions from 'src/redux/signup/signupActions';

export const ActionCreators = Object.assign(
  {},
  { ...ListingActions, ...AuthenticationActions, ...signupActions }
);
