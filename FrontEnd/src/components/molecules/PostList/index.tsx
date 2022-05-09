import React from 'react';
import Post from '@src/components/atoms/Post';

interface Post {
  id: string;
  imgSrc: string;
  title: string;
  content: string;
  region: string;
  price: string;
}

interface PostListProps {
  posts: Post[];
}
const PostList = ({ posts }: PostListProps) => {
  return (
    <div>
      {posts.map((post, i) => (
        <Post key={post.id + i} {...post} />
      ))}
    </div>
  );
};

export default PostList;
