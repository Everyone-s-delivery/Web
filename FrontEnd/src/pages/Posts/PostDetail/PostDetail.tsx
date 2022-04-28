import React from 'react';
import { useMatch } from 'react-router-dom';
import { ScrollPageWrapper } from '@src/components/@styled/layout';
import ArticleDescription from '@src/components/atoms/ArticleDescription';
import ArticleProfile from '@src/components/atoms/ArticleProfile';

const PostDetail = () => {
  const match = useMatch('/posts/:id');
  console.log(match);

  return (
    <>
      <ScrollPageWrapper>
        <ArticleProfile nickname="hyunjong nho" region="구리" />
        <hr></hr>
        <ArticleDescription
          category="치킨"
          description="치킨 같이 먹어요"
          title="치킨드실분~~"
          price="15000원"
        />
        <hr></hr>
      </ScrollPageWrapper>
    </>
  );
};
export default PostDetail;
