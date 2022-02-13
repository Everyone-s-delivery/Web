import { useEffect } from 'react';
import { useDispatch } from 'react-redux';

import { getListingAction } from 'src/redux/posts/postsActions';
import { useRootState } from 'src/utils/useRootState';
import { PostsReducerType } from './../../redux/posts/postsReducer';

const usePosts = (): PostsReducerType => {
  const dispatch = useDispatch();
  const { posts, loading, error } = useRootState((state) => state.posts);

  useEffect(() => {
    dispatch(getListingAction());
  }, [dispatch]);

  return {
    posts,
    loading,
    error,
  };
};
