import { css } from '@emotion/react';
import styled from '@emotion/styled';
import { LAYOUT, PAGE_WIDTH } from '@src/constants';

import { fadeIn } from './keyframes';
import { setDesktopMediaQuery, setLaptopMediaQuery, setTabletMediaQuery } from './mediaQueries';

export const ScrollPageWrapper = styled.div(
  ({ theme }) => css`
    width: 100%;
    height: 100%;
    overflow-y: scroll;
  `
);

export const Page = styled.main(
  () => css`
    width: 100%;
    min-height: 100%;
    padding-top: ${LAYOUT.HEADER_HEIGHT};
    overflow-x: hidden;

    animation: ${fadeIn} 1s forwards;

    ${setTabletMediaQuery`
      padding: ${LAYOUT.PAGE_MARGIN_TOP} 0.3125rem 0;
      width: ${PAGE_WIDTH.TABLET};
      margin: 0 auto;
    `}

    ${setLaptopMediaQuery`
      padding: ${LAYOUT.PAGE_MARGIN_TOP} 0.3125rem 0;
      width: ${PAGE_WIDTH.LAPTOP};
      margin: 0 auto;
    `}

    ${setDesktopMediaQuery`
      padding: ${LAYOUT.PAGE_MARGIN_TOP} 0.3125rem 0;
      width: ${PAGE_WIDTH.DESKTOP};
      margin: 0 auto;
    `}
  `
);
