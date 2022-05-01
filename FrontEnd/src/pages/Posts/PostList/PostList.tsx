import React from 'react';
import useInfiniteScroll from 'react-infinite-scroll-hook';
import Logo from '@src/assets/logo';
import { LoadingDots, Spinner } from '@src/components/@shared/Loader/Loader.style';
import { ScrollPageWrapper } from '@src/components/@styled/layout';
import { default as List } from '@src/components/molecules/PostList';
import { usePostsQuery } from '@src/services/queries/posts';

import { Container, ContentWrapper } from './PostList.style';

const PostList = () => {
  const { data, fetchNextPage, hasNextPage, isLoading, error } = usePostsQuery();

  const posts = data?.pages
    .map((pr) => pr.result)
    .flatMap((v) => v)
    .map((p) => ({
      id: p.postId,
      imgSrc: `../images/${p.imageId}.jpeg`,
      title: p.title,
      content: p.description,
      region: p.addresses[0],
      price: '인당 7000원',
    }));

  const [sentryRef] = useInfiniteScroll({
    loading: isLoading,
    hasNextPage: hasNextPage ?? false,
    onLoadMore: () => setTimeout(() => fetchNextPage(), 1500),
    disabled: !!error,
    rootMargin: '0px 0px 400px 0px',
  });

  if (!posts) {
    return null;
  }

  return (
    <ScrollPageWrapper>
      <Container>
        <ContentWrapper>
          <Logo width="100px" height="100px" />
          <p className="font-bold text-4xl p-6 ">글 목록</p>
          <List posts={posts ?? []} />
          <div className="mb-3 mt-6 flex flex-col items-center justify-center" ref={sentryRef}>
            <Spinner size={'48px'} isShown />
          </div>
        </ContentWrapper>
      </Container>
    </ScrollPageWrapper>
  );
};

export default PostList;
