import styled from '@emotion/styled';

const KAKAO_BUTTON_COLOR = '#fee500';
const KakaoLoginButton = styled.button<React.CSSProperties>`
  ${({ theme, backgroundColor, color }) => `
    color: ${color ?? theme.color.textColor};
    background-color: ${backgroundColor ?? theme.color.primaryColor};
  `}
  display: inline-flex;
  text-align: center;
  justify-content: center;
  transition: opacity 0.5s;
  width: 100%;
  height: 3rem;
  border: 0;
  border-radius: 0.75rem;
  background-color: ${KAKAO_BUTTON_COLOR};
  cursor: pointer;
  align-items: center;
  span {
    font-size: 1rem;
    align-items: center;
    color: rgba(0, 0, 0, 0.85);
    vertical-align: bottom;
  }
  svg {
    height: 1.5rem;
    margin-right: 0.75rem;
  }
  :hover {
    opacity: 0.5;
  }
`;
const KakaoLoginButton2 = styled.button`
  width: 100%;
  //   max-width: 20rem;
  height: 3rem;
  border: 0;
  border-radius: 0.75rem;
  background-color: ${KAKAO_BUTTON_COLOR};
  cursor: pointer;

  display: flex;
  flex-direction: column;
  align-itmes: center !important;
  justify-content: center !important;
  -webkit-box-pack: center;
  vertical-align: middle;
  -webkit-justify-content: center;
  svg {
    height: 1rem;
    margin-right: 0.75rem;
  }

  span {
    font-size: 1rem;
    align-items: center;
    color: rgba(0, 0, 0, 0.85);
    vertical-align: bottom;
  }
`;

export { KakaoLoginButton };
