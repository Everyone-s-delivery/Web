import { PostsResponse } from '@src/@types';
import { LOCAL_STORAGE_KEY, REQUEST_URL } from '@src/constants';
import APPLICATION_ERROR_CODE from '@src/constants/applicationErrorCode';
import { getLocalStorageItem } from '@src/utils/localStorage';
import axios, { AxiosError, AxiosRequestConfig } from 'axios';

axios.defaults.baseURL = 'http://15.165.151.207:8000'; //process.env.REACT_APP_

const STATUS_CODE = {
  INTERNAL_SERVER_ERROR: 500,
};

const isAxiosError = (error: any): error is AxiosError => {
  return error.isAxiosError;
};

const isNoAuthorizationRequired = (path: string) => {
  return [REQUEST_URL.LOGIN, REQUEST_URL.SIGNUP].includes(path);
};

const request = async (config: AxiosRequestConfig) => {
  try {
    const response = await axios(config);

    return response.data;
  } catch (error) {
    if (isAxiosError(error)) {
      if (!error.response) {
        throw {
          code: APPLICATION_ERROR_CODE.NETWORK_ERROR,
          message: 'Network Error',
        };
      }

      if (error.response.status === STATUS_CODE.INTERNAL_SERVER_ERROR) {
        throw {
          code: APPLICATION_ERROR_CODE.INTERNAL_SERVER_ERROR,
          message: 'internal server error',
        };
      }

      throw error.response?.data;
    }
  }
};

axios.interceptors.request.use(
  (config) => {
    if (
      (config.headers && !config.headers.Authorization) ||
      isNoAuthorizationRequired(config.url as string)
    ) {
      const token = getLocalStorageItem(LOCAL_STORAGE_KEY.ACCESS_TOKEN);

      if (token && config.headers) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }

    return config;
  },
  (error) => Promise.reject(error)
);

const API = {
  login: <T>(data: T) => {
    return request({ method: 'POST', url: REQUEST_URL.LOGIN, data });
  },
  signup: <T>(data: T) => {
    return request({ method: 'POST', url: REQUEST_URL.SIGNUP, data });
  },
  posts: (page?: number): Promise<PostsResponse> => {
    return page
      ? request({
          method: 'POST',
          url: `${REQUEST_URL.POSTS}/${page}`,
          data: {
            addresses: ['string'],
            endDate: '2022-03-22T11:43:28.044Z',
            keyColumn: 'REG_DATE',
            limit: 2,
            offset: 0,
            orderBy: 'ASC',
            posterIdList: [1],
            startDate: '2022-03-22T11:43:28.044Z',
            title: 'string',
          },
        })
      : request({ method: 'GET', url: REQUEST_URL.POSTS });
  },
};
export default API;
