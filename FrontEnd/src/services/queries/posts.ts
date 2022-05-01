import { useInfiniteQuery } from 'react-query';
import { ErrorResponse, Post, PostsResponse } from '@src/@types';
import { QUERY } from '@src/constants/queries';
import { AxiosError } from 'axios';

import API from '../requests';

interface FetchPostsResult {
  result: Post[];
  isLastPage: boolean;
  nextPage: number;
}
export const usePostsQuery = () => {
  const fetchPosts = async ({ pageParam = 1 }): Promise<FetchPostsResult> => {
    const data = await API.posts(pageParam);

    return {
      result: data.data,
      isLastPage: !data.next,
      nextPage: pageParam + 1,
    };
  };
  return useInfiniteQuery<FetchPostsResult, AxiosError<ErrorResponse>>(
    QUERY.GET_POSTS,
    fetchPosts,
    {
      getNextPageParam: (lastPage) => {
        if (!lastPage.isLastPage) return lastPage.nextPage;
      },
      cacheTime: 0,
      refetchOnMount: 'always',
    }
  );
};
