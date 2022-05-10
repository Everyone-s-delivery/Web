import styled from '@emotion/styled';
import { Page } from '@src/components/@styled/layout';

export const Container = styled(Page)`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: fixed;
  gap: 50px;
  padding-top: 0;
  width: 100%;
  height: 100%;
  background-color: ${({ theme }) => theme.color.appBackgroundColor};
`;
