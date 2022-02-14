export type TextSizeType = 'small' | 'medium' | 'large';

export const getTextSizeFromCode = (code: TextSizeType = 'medium'): string => {
  switch (code) {
    case 'small':
      return '1.4rem';
    case 'medium':
      return '1.6rem';
    case 'large':
      return '1.8rem';
  }
};
