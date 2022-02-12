import { useEffect } from 'react';
import { useDispatch } from 'react-redux';

import { getListingAction } from 'src/redux/posts/postsActions';
import { useRootState } from 'src/utils/useRootState';

const usePosts = () => {
  const dispatch = useDispatch();
  const { posts: list, loading, error } = useRootState((state) => state.posts);

  useEffect(() => {
    dispatch(getListingAction());
  }, [dispatch]);

  return {
    list,
    loading,
    error,
  };
};
