import { API_ERROR_MESSAGE } from 'src/constants/messages';

export interface Post {
  postId: number;
  posterId: number;
  posterEmail: string;
  posterNickName: string;
  title: string;
  description: string;
  addresses: string[];
  regDate: string;
  updateDate: string;
}

export interface PostsResponse {
  next: boolean;
  data: Post[];
}

export type APIErrorCode = keyof typeof API_ERROR_MESSAGE;

export type ErrorResponse = {
  errorCode: APIErrorCode;
};
