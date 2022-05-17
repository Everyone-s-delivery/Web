declare global {
  interface PostsResponseBody {
    next: boolean;
    data: Post[];
  }
  interface LoginResponseBody {
    token: string;
    userId: string;
  }
}

export {};
