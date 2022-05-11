import React from 'react';
import { Avatar } from '@mui/material';
interface ArticleDescriptionProps {
  title: string;
  category: string;
  price: string;
  description: string;
}
const ArticleDescription = ({ title, category, price, description }: ArticleDescriptionProps) => {
  return (
    <section className="mx-6 py-10 border-b-1 border-solid border-gray-400">
      <h1 className="text-4xl font-semibold -tracking-wider">{title}</h1>
      <p className="mt-2 text-base -tracking-wider text-gray-500">{category}</p>
      <p className="mt-2 text-2xl font-bold -tracking-wider text-gray-500">{price}</p>
      <div className="mt-3 mb-6">
        <p className="text-2xl -tracking-wider break-all mt-3 mb-5">{description}</p>
      </div>
    </section>
  );
};

function stringToColor(string: string) {
  let hash = 0;
  let i;

  /* eslint-disable no-bitwise */
  for (i = 0; i < string.length; i += 1) {
    hash = string.charCodeAt(i) + ((hash << 5) - hash);
  }

  let color = '#';

  for (i = 0; i < 3; i += 1) {
    const value = (hash >> (i * 8)) & 0xff;
    color += `00${value.toString(16)}`.slice(-2);
  }
  /* eslint-enable no-bitwise */

  return color;
}

function stringAvatar(name: string) {
  return {
    sx: {
      bgcolor: stringToColor(name),
    },
    children: `${name.split(' ')[0][0]}${name.split(' ')[1][0]}`,
  };
}

export default ArticleDescription;
