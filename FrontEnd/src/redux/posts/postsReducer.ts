import { Action, ActionType, Listing } from '@src/model/model';

import createReducer from '../createReducer';

export interface PostsReducerType {
  posts: Listing[];
  loading: boolean;
  error?: string;
}
const defaultState: PostsReducerType = {
  posts: [],
  loading: false,
  error: undefined,
};

export const postsReducer = createReducer<PostsReducerType>(defaultState, {
  [ActionType.POSTS_REQUEST](state: PostsReducerType, action: Action<Listing[]>) {
    return {
      ...state,
      loading: true,
    };
  },

  [ActionType.POSTS_REQUEST_ERROR](state: PostsReducerType, action: Action<any>) {
    return {
      ...state,
      loading: false,
      error: action.payload,
    };
  },

  [ActionType.POSTS_REQUEST_SUCCESS](state: PostsReducerType, action: Action<Listing[]>) {
    return {
      ...state,
      loading: false,
      list: action.payload,
    };
  },
});
