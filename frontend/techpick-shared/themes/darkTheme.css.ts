import { createTheme } from '@vanilla-extract/css';
import { colorThemeContract } from './colorThemeContract.css';

export const darkTheme = createTheme(colorThemeContract, {
  color: {
    primary: '#00306e',
    secondary: '#95004d',
    border: '#3e3e3e',
    background: '#000',
    font: '#fff',
    buttonPrimary: '#888F95',
    buttonSecondary: '#888888',
    buttonWarning: '#CC4C4C',
    buttonPrimaryFocus: '#6A7B7D',
    buttonWarningFocus: '#FF5C5C',
    inputBackground: '#1a1a1a', // 입력 필드 기본 배경색
    inputBorderFocus: '#444C52', // 입력 필드 포커스 시 경계 색상
    inputBackgroundFocus: '#333333', // 입력 필드 포커스 시 배경색,
    tagSelectedBackground: '#2e3236', // 조정된 태그 선택된 상태 배경색
    tagBackground: '#2a2a2a', // 기본 태그 배경색
    tagBorder: '#3a3a3a',
    popoverButtonHover: '#3a3f42',
  },
});
