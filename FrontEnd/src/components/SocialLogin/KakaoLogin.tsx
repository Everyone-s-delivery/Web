import React from 'react';

import { KakaoIcon } from '../@Icons';
import { KakaoLoginButton } from './KakaoLogin.styles';

const KAKA0_HOST = 'https://kauth.kakao.com/oauth/authorize';
const CID = 'aaa'; //process.env.SNOWPACK_PUBLIC_KAKAO_CLIENT_ID;
const REDIRECT_URL = 'ggg'; //process.env.SNOWPACK_PUBLIC_LOGIN_REDIRECT_URL;

const requestUri = `${KAKA0_HOST}?client_id=${CID}&redirect_uri=${REDIRECT_URL}&response_type=code`;

const KakaoLogin = () => {
  return (
    <a href={requestUri}>
      <KakaoLoginButton>
        <KakaoIcon />
        카카오 로그인
      </KakaoLoginButton>
    </a>
  );
};

export default KakaoLogin;
