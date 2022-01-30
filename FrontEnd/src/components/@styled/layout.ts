import styled, { css } from 'styled-components';

import { customScrollbarCSS } from './scrollbar';
export const ScrollPageWrapper = styled.div(
  ({ theme }) => css`
    width: 100%;
    height: 100%;
    overflow-y: scroll;

    ${customScrollbarCSS(theme.color.tagItemColor)}
  `
);
