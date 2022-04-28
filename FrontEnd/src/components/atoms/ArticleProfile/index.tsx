import React from 'react';
import { Avatar } from '@mui/material';
interface ArticleProfileProps {
  nickname: string;
  region: string;
}
const ArticleProfile = ({ nickname, region }: ArticleProfileProps) => {
  return (
    <section className="mx-6 py-10 relative border-b-1 border-solid border-gray-300">
      <h3 className="absolute left-[-9999px] top-[-9999px]"></h3>
      <div className="flex items-center justify-between">
        <div className="flex">
          <Avatar {...stringAvatar(nickname)} sx={{ width: '40px', height: '40px' }} />
        </div>
        <div className="inline-block ml-3 align-middle">
          <div className="text-xl font-semibold -tracking-wide">{nickname}</div>
          <div className="text-base -tracking-wide">{region}</div>
        </div>
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

export default ArticleProfile;
