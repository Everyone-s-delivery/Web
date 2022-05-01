module.exports = {
  content: ['./src/**/*.{html,js,ts,tsx}'],
  theme: {
    fontSize: {
      '3xl': [
        '1.6rem',
        {
          lineHeight: '1.9rem',
        },
      ],
      '2xl': [
        '1.5rem',
        {
          lineHeight: '1.7rem',
        },
      ],
      xl: [
        '1.4rem',
        {
          lineHeight: '1.5rem',
        },
      ],
    },
  },
  plugins: [require('@tailwindcss/line-clamp')],
};
