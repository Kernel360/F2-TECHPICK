import { createTheme } from '@vanilla-extract/css';
import { colorThemeContract } from './colorThemeContract.css';

export const lightTheme = createTheme(colorThemeContract, {
  color: {
    primary: '#0070f3',
    secondary: '#ff0080',
    border: '#d3d3d3',
    background: '#fff',
    font: '#000',
  },
});
