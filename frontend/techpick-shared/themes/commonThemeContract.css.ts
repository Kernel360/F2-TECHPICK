import { createThemeContract } from '@vanilla-extract/css';

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
    fontWeight: {
      regular: '',
      semibold: '',
      bold: '',
      extrabold: '',
    },
  },
  breakpoints: {
    mobile: '',
    tablet: '',
    desktop: '',
  },
});

export const { breakpoints, space, typography } = commonThemeContract;
export const { fontColor, fontFamily, fontSize, fontWeight, lineHeight } =
  typography;
