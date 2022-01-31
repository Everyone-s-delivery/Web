import { fireEvent, screen } from '@testing-library/react';

import { customRender } from 'src/tests/customRenderer';
import SignupScreen from './Signup';

import '@testing-library/jest-dom';

describe('사용자는 회원가입 페이지에서 정보를 입력하고 가입할 수 있다.', () => {
  beforeEach(async () => {
    customRender({ initialEntries: ['/signup'], children: <SignupScreen /> });
  });

  it('사용자는 로그인 페이지에서 회원가입 버튼을 클릭할 수 있다.', async () => {
    const signup = screen.getByRole('button', { name: /회원가입/ }) as Element;
    expect(signup).toBeVisible();
    expect(signup).toBeEnabled();
    fireEvent.click(signup);
  });

  it('이메일을 입력할 수 있다.', async () => {
    const emailInput = screen.getByRole('textbox', { name: '이메일' }) as Element;

    expect(emailInput).toBeRequired();
    expect(emailInput).toBeVisible();
    const nicknameInput = screen.getByRole('textbox', { name: '닉네임' }) as Element;

    expect(nicknameInput).toBeRequired();
    expect(nicknameInput).toBeVisible();
    const addressInput = screen.getByRole('textbox', { name: '주소지' }) as Element;

    expect(addressInput).toBeRequired();

    expect(addressInput).toBeVisible();
  });
  it('닉네임을 입력할 수 있다.', async () => {
    const nicknameInput = screen.getByRole('textbox', { name: '닉네임' }) as Element;

    expect(nicknameInput).toBeRequired();
    expect(nicknameInput).toBeVisible();
  });
  it('주소지를 입력할 수 있다.', async () => {
    const addressInput = screen.getByRole('textbox', { name: '주소지' }) as Element;

    expect(addressInput).toBeRequired();

    expect(addressInput).toBeVisible();
  });
});
