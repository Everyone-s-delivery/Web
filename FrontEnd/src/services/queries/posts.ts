import { useInfiniteQuery } from 'react-query';
import { ErrorResponse, Post } from '@src/@types';
import { QUERY } from '@src/constants/queries';
import { AxiosError } from 'axios';

import API from '../requests';

export const usePostsQuery = () => {
  return useInfiniteQuery<Post[] | null, AxiosError<ErrorResponse>>(
    QUERY.GET_POSTS,
    async () => {
      return await API.posts();
    },
    {
      getNextPageParam: (_, pages) => {
        return pages.length;
      },
      cacheTime: 0,
      refetchOnMount: 'always',
    }
  );
};
