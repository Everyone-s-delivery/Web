import { css } from '@emotion/react';

export const globalStyle = css`
  html,
  body {
    font-size: 62.5%;
  }
  #root {
    margin: 0;
    padding: 0;
    height: 100%;
    font-size: 16px;
    font-family: 'Noto Sans KR', sans-serif;
  }

  body {
    background-size: cover;
    background-position: center;
    background-repeat: no-repeat;
  }

  svg {
    display: block;
  }

  button {
    border: none;
    background-color: transparent;
    outline: none;
    padding: 0px;
    cursor: pointer;
  }

  ul {
    list-style: none;
    padding: 0;
    margin: 0;
  }

  input[type='number']::-webkit-outer-spin-button,
  input[type='number']::-webkit-inner-spin-button {
    -webkit-appearance: none;
    -moz-appearance: none;
    appearance: none;
  }

  textarea {
    resize: none;
  }

  a {
    font-weight: bold;
    font-size: 2rem;
    display: block;
    margin: 20px;
    all: unset;
    color: #3f464d;
  }

  a:link {
    text-decoration: none;
  }
  a:visited {
    text-decoration: none;
  }
  a:active {
    text-decoration: none;
  }
  a:hover {
    text-decoration: none;
    cursor: pointer;
  }

  a,
  button {
    -webkit-tap-highlight-color: transparent;
  }

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
  }
`;
