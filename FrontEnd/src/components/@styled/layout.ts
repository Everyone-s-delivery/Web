import { LAYOUT, PAGE_WIDTH } from '@src/constants';
import styled, { css } from 'styled-components';

import { fadeIn } from './keyframes';
import { setDesktopMediaQuery, setLaptopMediaQuery, setTabletMediaQuery } from './mediaQueries';
import { customScrollbarCSS } from './scrollbar';
export const ScrollPageWrapper = styled.div(
  ({ theme }) => css`
    width: 100%;
    height: 100%;
    overflow-y: scroll;

    ${customScrollbarCSS(theme.color.tagItemColor)}
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
