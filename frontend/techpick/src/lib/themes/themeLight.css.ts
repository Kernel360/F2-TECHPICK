import { createTheme } from '@vanilla-extract/css';
import { themeVars } from '@/lib/themes/themeContract.css';

export const lightThemeClass = createTheme(themeVars, {
  color: {
    primary: '#0070f3',
    secondary: '#ff0080',
    border: '#d3d3d3',
    background: '#fff',
  },
  space: {
    xs: '4px',
    sm: '8px',
    md: '16px',
    lg: '24px',
    xl: '32px',
  },
  typography: {
    fontFamily: 'Arial, sans-serif',
    fontColor: '#000',
    fontSize: {
      xs: '12px',
      sm: '14px',
      md: '16px',
      lg: '20px',
      xl: '24px',
    },
    lineHeight: {
      xs: '16px',
      sm: '20px',
      md: '24px',
      lg: '28px',
      xl: '32px',
    },
  },
  breakpoints: {
    mobile: '576px',
    tablet: '768px',
    desktop: '1024px',
  },
});
