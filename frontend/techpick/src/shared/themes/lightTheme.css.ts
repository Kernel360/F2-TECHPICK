import { createTheme } from '@vanilla-extract/css';
import { themeContract } from '@/shared/themes/themeContract.css';

export const lightTheme = createTheme(themeContract, {
  color: {
    primary: '#0070f3',
    secondary: '#ff0080',
    border: '#d3d3d3',
    background: '#fff',
    font: '#000',
  },
});
