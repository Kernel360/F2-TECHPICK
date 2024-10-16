import { createThemeContract } from '@vanilla-extract/css';

export const colorThemeContract = createThemeContract({
  color: {
    primary: '',
    secondary: '',
    border: '',
    background: '',
    font: '',
    buttonPrimary: '', // 버튼 기본 색상
    buttonSecondary: '',
    buttonWarning: '',
    buttonPrimaryFocus: '', // 버튼 focus 색상
    buttonWarningFocus: '',
    inputBackground: '', // 입력 필드 배경색
    inputBorderFocus: '', // 입력 필드 포커스 시 경계 색상
    inputBackgroundFocus: '', // 입력 필드 포커스 시 배경색
    tagSelectedBackground: '', // 태그 선택된 상태 배경색
    tagBackground: '', // 태그 기본 배경색
    tagBorder: '',
    popoverButtonHover: '', // 팝업 트리거 버튼 hover 색상
  },
});

export const { color } = colorThemeContract;
