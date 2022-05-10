declare global {
  interface RequestValidationError {
    status: number;
    message: string;
  }
}

export {};
