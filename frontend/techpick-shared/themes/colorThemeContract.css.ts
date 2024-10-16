import { createThemeContract } from '@vanilla-extract/css';

export const colorThemeContract = createThemeContract({
  color: {
    primary: '',
    secondary: '',
    border: '',
    background: '',
    font: '',
  },
});
