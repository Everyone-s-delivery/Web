import { Page } from '@src/components/@styled/layout';
import styled from 'styled-components';

export const Container = styled(Page)`
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  padding-top: 0;
  width: 100%;
  height: 100%;
  background-color: ${({ theme }) => theme.color.appBackgroundColor};
`;
