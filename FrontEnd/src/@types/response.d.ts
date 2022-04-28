declare global {
  interface PostsResponseBody {
    next: boolean;
    data: Post[];
  }
}

export {};
