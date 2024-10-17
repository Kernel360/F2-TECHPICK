import { createTheme } from '@vanilla-extract/css';
import { commonThemeContract } from './commonThemeContract.css';

export const commonTheme = createTheme(commonThemeContract, {
  space: {
    xs: '4px',
    sm: '8px',
    md: '16px',
    lg: '24px',
    xl: '32px',
  },
  typography: {
    fontFamily: 'Arial, sans-serif', // todo: 나중에 제거.
    fontColor: '#fff',
    fontSize: {
      xs: '0.75rem', // 12
      sm: '0.875rem', // 14
      md: '1rem', // 16
      lg: '1.25rem', // 20
      xl: '1.5rem', // 24
    },
    lineHeight: {
      xs: '1rem', // 16px
      sm: '1.25rem', // 20px
      md: '1.5rem', // 24px
      lg: '1.75rem', // 28px
      xl: '2rem', // 32px
    },
    fontWeight: {
      regular: '400',
      semibold: '600',
      bold: '700',
      extrabold: '800',
    },
  },
  breakpoints: {
    mobile: '576px',
    tablet: '768px',
    desktop: '1024px',
  },
});
