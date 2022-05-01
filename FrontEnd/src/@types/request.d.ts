declare global {
  interface PostsRequestBody {
    page: number;
    pageSize: number;
    'search.startDate': string;
    'search.endDate': string;
  }
}

export {};
