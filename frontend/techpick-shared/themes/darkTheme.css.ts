import { createTheme } from '@vanilla-extract/css';
import { colorThemeContract } from './colorThemeContract.css';

export const darkTheme = createTheme(colorThemeContract, {
  color: {
    primary: '#00306e',
    secondary: '#95004d',
    border: '#eaeaea',
    background: '#000',
    font: '#fff',
  },
});
