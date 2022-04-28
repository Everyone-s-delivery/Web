import { API_ERROR_MESSAGE } from 'src/constants/messages';

declare global {
  interface Post {
    postId: number;
    posterId: number;
    posterEmail: string;
    posterNickName: string;
    title: string;
    description: string;
    addresses: string[];
    regDate: string;
    updateDate: string;
    imageId: string;
  }

  interface ErrorResponse {
    errorCode: APIErrorCode;
  }

  type APIErrorCode = keyof typeof API_ERROR_MESSAGE;
}

export {};
