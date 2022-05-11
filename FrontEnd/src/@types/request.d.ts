declare global {
  interface PostsRequestBody {
    page: number;
    pageSize: number;
    'search.startDate': string;
    'search.endDate': string;
  }

  interface LoginRequestBody {
    email: string;
    password: string;
  }
}

export {};
