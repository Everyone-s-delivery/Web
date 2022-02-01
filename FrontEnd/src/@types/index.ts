import { API_ERROR_MESSAGE } from 'src/constants/messages';

export interface Post {
  id: number;
  content: string;
  authorName: string;
  createdAt: string;
  updatedAt: string;
  comments: string[];
}

export type APIErrorCode = keyof typeof API_ERROR_MESSAGE;

export type ErrorResponse = {
  errorCode: APIErrorCode;
};
