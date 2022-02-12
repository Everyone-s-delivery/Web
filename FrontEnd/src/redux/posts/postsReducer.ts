import { Action, ActionType, Listing } from 'src/model/model';
import createReducer from '../createReducer';

export interface PostsReducerType {
  posts: Listing[];
  loading: boolean;
  error?: string;
  nextpage: string;
}
const defaultState: PostsReducerType = {
  posts: [],
  loading: false,
  error: undefined,
  nextpage: '',
};

export const listingReducer = createReducer<PostsReducerType>(defaultState, {
  [ActionType.LISTING_REQUEST](state: PostsReducerType, action: Action<Listing[]>) {
    return {
      ...state,
      loading: true,
    };
  },

  [ActionType.LISTING_REQUEST_ERROR](state: PostsReducerType, action: Action<any>) {
    return {
      ...state,
      loading: false,
      error: action.payload,
    };
  },

  [ActionType.LISTING_REQUEST_SUCCESS](state: PostsReducerType, action: Action<Listing[]>) {
    return {
      ...state,
      loading: false,
      list: action.payload,
    };
  },
});
