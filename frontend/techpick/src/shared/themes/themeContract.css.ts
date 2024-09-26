import { createThemeContract } from '@vanilla-extract/css';

export const themeContract = createThemeContract({
  color: {
    primary: '',
    secondary: '',
    border: '',
    background: '',
    font: '',
  },
});

export const commonThemeContract = createThemeContract({
  space: {
    xs: '',
    sm: '',
    md: '',
    lg: '',
    xl: '',
  },
  typography: {
    fontFamily: '',
    fontColor: '',
    fontSize: {
      xs: '',
      sm: '',
      md: '',
      lg: '',
      xl: '',
    },
    lineHeight: {
      xs: '',
      sm: '',
      md: '',
      lg: '',
      xl: '',
    },
  },
  breakpoints: {
    mobile: '',
    tablet: '',
    desktop: '',
  },
});
