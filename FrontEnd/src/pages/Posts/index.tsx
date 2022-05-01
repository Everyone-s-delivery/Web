import React from 'react';
import { Route, Routes } from 'react-router-dom';

import PostDetail from './PostDetail/PostDetail';
import PostList from './PostList/PostList';

const Posts = () => {
  return (
    <Routes>
      <Route index element={<PostList />} />
      <Route path=":id" element={<PostDetail />} />
    </Routes>
  );
};

export default Posts;
