import { LOCAL_STORAGE_KEY, REQUEST_URL } from '@src/constants';
import APPLICATION_ERROR_CODE from '@src/constants/applicationErrorCode';
import { getLocalStorageItem } from '@src/utils/localStorage';
import axios, { AxiosError, AxiosRequestConfig } from 'axios';

axios.defaults.baseURL = 'http://15.165.151.207:8000'; // 'http://127.0.0.1:5050/';

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
  login: (data: LoginRequestBody): Promise<LoginResponseBody> =>
    request({ method: 'POST', url: REQUEST_URL.LOGIN, data }),
  signup: <T>(data: T) => {
    return request({ method: 'POST', url: REQUEST_URL.SIGNUP, data });
  },
  posts: (page?: number): Promise<void> => {
    return request({
      method: 'GET',
      url: REQUEST_URL.POSTS,
      params: {
        page: page ?? 1,
        pageSize: 10,
        'search.startDate': new Date().valueOf(),
        'search.endDate': new Date().valueOf(),
      },
    });
  },
};
export default API;
