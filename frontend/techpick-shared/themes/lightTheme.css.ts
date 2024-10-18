import { createTheme } from '@vanilla-extract/css';
import { colorThemeContract } from './colorThemeContract.css';

export const lightTheme = createTheme(colorThemeContract, {
  color: {
    primary: '#0070f3',
    secondary: '#ff0080',
    border: '#d3d3d3',
    background: '#ffffff',
    font: '#000',
    buttonPrimary: '#444C52',
    buttonSecondary: '#666666',
    buttonWarning: '#CC4C4C',
    buttonPrimaryFocus: 'black',
    buttonWarningFocus: '#FF5C5C',
    inputBackground: '#f9f9f9', // 입력 필드 기본 배경색
    inputBorderFocus: '#444C52', // 입력 필드 포커스 시 경계 색상
    inputBackgroundFocus: '#ffffff', // 입력 필드 포커스 시 배경색
    tagSelectedBackground: '#f5f5f4', // 태그 선택된 상태 배경색
    tagBackground: 'transparent', // 기본 태그 배경색
    tagBorder: 'none',
    popoverButtonHover: '#e0e0e0',
  },
});
