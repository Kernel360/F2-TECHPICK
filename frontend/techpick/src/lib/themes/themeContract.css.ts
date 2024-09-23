import { createThemeContract } from '@vanilla-extract/css';

export const themeVars = createThemeContract({
  color: {
    primary: '',
    secondary: '',
    border: '',
    background: '',
  },
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
