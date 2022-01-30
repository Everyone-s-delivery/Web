import { fireEvent, screen } from '@testing-library/react';

import { customRender } from 'src/tests/customRenderer';
import LoginScreen from './LoginScreen';

import '@testing-library/jest-dom';

describe('사용자는 로그인 페이지에서 로그인과 회원가입을 할 수 있다.', () => {
  beforeEach(async () => {
    customRender({ initialEntries: ['/login'], children: <LoginScreen /> });
  });

  it('사용자는 로그인 페이지에서 회원가입 버튼을 클릭할 수 있다.', async () => {
    const signup = screen.getByText('회원 가입').closest('a') as Element;

    expect(signup).toHaveAttribute('href', '/signup');
    expect(signup).toBeEnabled();
    fireEvent.click(signup);
  });

  it('사용자는 로그인 페이지에서 카카오톡 로그인 버튼을 클릭할 수 있다.', async () => {
    const loginButton = screen.getByText('카카오 로그인').closest('a') as Element;

    expect(loginButton).toHaveAttribute('href');
    expect(loginButton).toBeEnabled();
    fireEvent.click(loginButton);
  });

  it('사용자는 로그인 페이지에서 아이디를 입력할 수 있다.', async () => {
    const idBtn = screen.getByPlaceholderText('everyone@everyone.com') as Element;

    expect(idBtn).toBeRequired();
    expect(idBtn).toBeEnabled();
    fireEvent.click(idBtn);
  });

  it('사용자는 로그인 페이지에서 비밀번호를 입력할 수 있다.', async () => {
    const pwBtn = screen.getByText('비밀번호').closest('div') as Element;
    expect(pwBtn).toBeEnabled();
    fireEvent.click(pwBtn);
  });
});
