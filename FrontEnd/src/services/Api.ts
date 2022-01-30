import { getCookie } from '../utils/cookies';

export const apiBaseRoute = 'http://15.165.151.207:8000'; // 'http://192.168.21.76:8000'; // process.env.REACT_APP_API_BASE_URL;

export enum MethodType {
  POST = 'POST',
  GET = 'GET',
  PUT = 'PUT',
  DELETE = 'DELETE',
}

export const loginRoute = `/signin`;
export const signupRoute = `/signup`;
export const listingRoute = `/api/sale_listings`;

export const createApiCall = async ({
  method = 'GET',
  url = '',
  data = {},
  auth = false,
}) => {
  const headers: any = {
    'Content-Type': 'application/json',
  };
  if (auth) {
    headers['Authorization'] = getCookie('token');
  }
  return fetch(encodeURI(`${apiBaseRoute}${url}`), {
    body: method === 'GET' ? undefined : JSON.stringify(data),
    cache: 'no-cache',
    headers,
    method,
  })
    .then((response) => response.json())
    .then((result) => result);
};
