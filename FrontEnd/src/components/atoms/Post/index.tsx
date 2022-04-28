import React from 'react';

interface PostProps {
  id: string;
  imgSrc: string;
  title: string;
  content: string;
  region: string;
  price: string;
}

const Post = ({ id, imgSrc, title, content, region, price }: PostProps) => {
  return (
    <article className="relative text-left block w-full divide-y divide-solid">
      <a className="block no-underline text-black pl-56 py-6 pr-7 h-44" href={`./posts/${id}`}>
        <div className="absolute left-7 right-7 bottom-7 top-7 h-44 w-44 overflow-hidden bg-white rounded-t-xl rounded-b-xl">
          <img className="w-full block align-middle" src={imgSrc} />
        </div>
        <div>
          <div className="text-ellipsis block webkit line-clamp-2 orient text-3xl mt-3 text-black">
            <span className="font-bold">{title}</span>
            <span> {content}</span>
          </div>
        </div>
        <p className="text-xl mt-2 text-gray-500 inline-block">{region}</p>
        <p className="text-2xl mt-3 text-[#5348ec]">{price}</p>
      </a>
      <hr />
    </article>
  );
};

export default Post;
