import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import Logo from '@src/assets/logo';
import { KakaoIcon } from '@src/components/@Icons';
import Button from '@src/components/atoms/Button';
import TextField from '@src/components/atoms/TextField';
import { loginActions } from '@src/redux/login/loginSlice';
import { useRootState } from '@src/utils/useRootState';
import { Form, Formik, FormikProps } from 'formik';
import * as Yup from 'yup';

import { Container } from './Login.style';

interface LoginFormValue {
  email: string;
  password: string;
}

const Login = () => {
  const navigate = useNavigate();

  const dispatch = useDispatch();
  const { payload } = useRootState((state) => state.loginForm);

  useEffect(() => {
    if (payload) {
      const { token, userId } = payload;
      if (token && userId) {
        navigate('/posts');
      }
    }
  }, [payload, dispatch]);

  const validationSchema = () => {
    return Yup.object().shape({
      email: Yup.string()
        .email('유효한 이메일 형식을 입력하세요')
        .required('가입한 이메일 주소를 입력하세요.'),
      password: Yup.string()
        .min(8, ({ min }) => `비밀번호는 최소 ${min}글자 이상입니다.`)
        .required('비밀번호를 입력하세요.'),
    });
  };

  const handleLogin = (formValue: LoginFormValue) => {
    const { email, password } = formValue;
    dispatch(loginActions.login({ email, password }));
  };

  return (
    <div className="flex justify-center">
      <Container>
        <Logo />
        <Formik
          initialValues={{
            email: '',
            password: '',
          }}
          validationSchema={validationSchema}
          onSubmit={(values: LoginFormValue) => {
            handleLogin(values);
          }}
        >
          {({
            errors,
            touched,
            handleSubmit,
            handleChange,
            values,
          }: FormikProps<LoginFormValue>) => (
            <Form className="flex flex-col gap-4 w-full" onSubmit={handleSubmit}>
              <div className="flex flex-col mb-10">
                <TextField
                  required
                  type={'email'}
                  name="email"
                  label="아이디"
                  onChange={handleChange}
                  value={values.email}
                />
                {touched.email && errors.email && (
                  <div className="text-red-900">{errors.email}</div>
                )}
                <TextField
                  required
                  type={'password'}
                  name="password"
                  label="비밀번호"
                  onChange={handleChange}
                  value={values.password}
                />
                {touched.password && errors.password && (
                  <div className="text-red-900">{errors.password}</div>
                )}
              </div>
              <div className="flex flex-col gap-10">
                <Button
                  innerText="로그인"
                  buttonColor={'olive'}
                  textColor={'white'}
                  hasFixedWidth
                  hasShadow
                  textSize="large"
                />
                <Button
                  innerText="로그인"
                  buttonColor={'cafe_yellow'}
                  textColor={'black'}
                  hasFixedWidth
                  hasShadow
                  textSize="large"
                  icon={<KakaoIcon />}
                />
              </div>
            </Form>
          )}
        </Formik>
      </Container>
    </div>
  );
};

export default Login;
