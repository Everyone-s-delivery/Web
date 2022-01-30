import { ActionType } from 'src/model/model';

export const getListingAction = (payload?: string) => {
  return {
    type: ActionType.LISTING_REQUEST,
    payload,
  };
};
