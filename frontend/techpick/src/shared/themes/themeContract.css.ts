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
