import { Page } from '@src/components/@styled/layout';
import styled from 'styled-components';

export const Container = styled(Page)<React.CSSProperties>`
  background-color: ${({ theme }) => theme.color.white};
  height: 100vh;
`;

export const ContentWrapper = styled.div`
  padding: 1.4375rem;
`;
