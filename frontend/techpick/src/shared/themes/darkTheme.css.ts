import { createTheme } from '@vanilla-extract/css';
import { themeContract } from '@/shared/themes/themeContract.css';

export const darkTheme = createTheme(themeContract, {
  color: {
    primary: '#00306e',
    secondary: '#95004d',
    border: '#3e3e3e',
    background: '#000',
    font: '#fff',
  },
});
