import React from 'react';
import Logo from '@src/assets/logo';
import { ScrollPageWrapper } from '@src/components/@styled/layout';
import { default as List } from '@src/components/molecules/PostList';
import { usePostsQuery } from '@src/services/queries/posts';

import { Container, ContentWrapper } from './PostList.style';

const PostList = () => {
  // const dispatch = useDispatch();
  // dispatch(getListingAction());

  const { data } = usePostsQuery();

  return (
    <ScrollPageWrapper>
      <Container>
        <ContentWrapper>
          <Logo width="100px" height="100px" />
          <p className="font-bold text-4xl p-6 ">글 목록</p>
          <List
            posts={[
              {
                id: '1',
                title: '치킨 같이 시키실 분!!',
                content: '치킨이 먹고 싶은데 같이 드실 분 계신가요 연락주세요 기다립니다.',
                imgSrc:
                  'https://dnvefa72aowie.cloudfront.net/origin/article/202204/a052ecc38500875d8c30fc1240f831d72bd9638d455218df5c58a192585a7f8e.webp?q=82&s=300x300&t=crop',
                price: '인당 5,000원',
                region: '수택아파트 102동 앞 놀이터',
              },
              {
                id: '2',
                title: '족발 드실분~~!',
                content: '족발 같이 드실 분 같이 먹어요 4명 구합니다~~~!!',
                imgSrc:
                  'https://dnvefa72aowie.cloudfront.net/origin/article/202204/f712f192dd95173e166b37aa11314820ca683126ee6cc179b4f1c69c3432a22d.webp?q=82&s=300x300&t=crop',
                price: '인당 7,000원',
                region: '주공아파트 201동 앞',
              },
              {
                id: '3',
                title: '족발 드실분~~!',
                content: '족발 같이 드실 분 같이 먹어요 4명 구합니다~~~!!',
                imgSrc:
                  'https://dnvefa72aowie.cloudfront.net/origin/article/202204/f712f192dd95173e166b37aa11314820ca683126ee6cc179b4f1c69c3432a22d.webp?q=82&s=300x300&t=crop',
                price: '인당 7,000원',
                region: '주공아파트 201동 앞',
              },
              {
                id: '4',
                title: '치킨 같이 시키실 분!!',
                content: '치킨이 먹고 싶은데 같이 드실 분 계신가요 연락주세요 기다립니다.',
                imgSrc:
                  'https://dnvefa72aowie.cloudfront.net/origin/article/202204/a052ecc38500875d8c30fc1240f831d72bd9638d455218df5c58a192585a7f8e.webp?q=82&s=300x300&t=crop',
                price: '인당 5,000원',
                region: '수택아파트 102동 앞 놀이터',
              },
              {
                id: '5',
                title: '족발 드실분~~!',
                content: '족발 같이 드실 분 같이 먹어요 4명 구합니다~~~!!',
                imgSrc:
                  'https://dnvefa72aowie.cloudfront.net/origin/article/202204/f712f192dd95173e166b37aa11314820ca683126ee6cc179b4f1c69c3432a22d.webp?q=82&s=300x300&t=crop',
                price: '인당 7,000원',
                region: '주공아파트 201동 앞',
              },
              {
                id: '6',
                title: '치킨 같이 시키실 분!!',
                content: '치킨이 먹고 싶은데 같이 드실 분 계신가요 연락주세요 기다립니다.',
                imgSrc:
                  'https://dnvefa72aowie.cloudfront.net/origin/article/202204/a052ecc38500875d8c30fc1240f831d72bd9638d455218df5c58a192585a7f8e.webp?q=82&s=300x300&t=crop',
                price: '인당 5,000원',
                region: '수택아파트 102동 앞 놀이터',
              },
            ]}
          />
        </ContentWrapper>
      </Container>
    </ScrollPageWrapper>
  );
};

export default PostList;
