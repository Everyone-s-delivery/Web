import React from 'react';
import { useDispatch } from 'react-redux';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import Container from '@mui/material/Container';
import Grid from '@mui/material/Grid';
import Link from '@mui/material/Link';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import { useFormik } from 'formik';
import * as Yup from 'yup';

import { SignupData } from 'src/model/model';
import { signupAction } from 'src/redux/signup/signupActions';

const SignupScreen = () => {
  const dispatch = useDispatch();
  const validationSchema: Yup.SchemaOf<SignupData> = Yup.object().shape({
    email: Yup.string()
      .email('유효한 이메일 형식을 입력하세요')
      .required('이메일 주소를 입력하세요.'),
    nickName: Yup.string().required('닉네임을 입력하세요.'),
    password: Yup.string()
      .min(8, '비밀번호는 최소 8글자 이상입니다.')
      .required('비밀번호를 입력하세요.'),
    address: Yup.string().required('주소를 입력하세요.'),
  });

  const formik = useFormik({
    initialValues: {
      email: '',
      nickName: '',
      password: '',
      address: '',
    },
    validationSchema: validationSchema,
    onSubmit: (values: SignupData) => {
      alert(JSON.stringify(values, null, 2));
      dispatch(signupAction(values));
    },
  });

  return (
    <Container component="main" maxWidth="xs">
      <Box
        sx={{
          marginTop: 8,
          display: 'flex',
          flexDirection: 'column',
          alignItems: 'center',
        }}
      >
        <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
          <LockOutlinedIcon />
        </Avatar>
        <Typography component="h1" variant="h5">
          회원가입
        </Typography>
        <Box component="form" noValidate onSubmit={formik.handleSubmit} sx={{ mt: 3 }}>
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="email"
                label="이메일"
                name="email"
                autoComplete="email"
                value={formik.values.email}
                onChange={formik.handleChange}
                error={formik.touched.email && Boolean(formik.errors.email)}
                helperText={formik.touched.email && formik.errors.email}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                id="nickName"
                label="닉네임"
                name="nickName"
                value={formik.values.nickName}
                onChange={formik.handleChange}
                error={formik.touched.nickName && Boolean(formik.errors.nickName)}
                helperText={formik.touched.nickName && formik.errors.nickName}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="password"
                label="비밀번호"
                type="password"
                id="password"
                autoComplete="new-password"
                value={formik.values.password}
                onChange={formik.handleChange}
                error={formik.touched.password && Boolean(formik.errors.password)}
                helperText={formik.touched.password && formik.errors.password}
              />
            </Grid>
            <Grid item xs={12}>
              <TextField
                required
                fullWidth
                name="address"
                label="주소지"
                type="address"
                id="address"
                autoComplete="address"
                value={formik.values.address}
                onChange={formik.handleChange}
                error={formik.touched.address && Boolean(formik.errors.address)}
                helperText={formik.touched.address && formik.errors.address}
              />
            </Grid>
          </Grid>
          <Button type="submit" fullWidth variant="contained" sx={{ mt: 3, mb: 2 }}>
            회원가입
          </Button>
          <Grid container justifyContent="flex-end">
            <Grid item>
              <Link href="/login" variant="body2">
                이미 계정이 있으신가요? 로그인 하러 가기
              </Link>
            </Grid>
          </Grid>
        </Box>
      </Box>
    </Container>
  );
};

export default SignupScreen;
