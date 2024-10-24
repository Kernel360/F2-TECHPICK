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

  primary: null,
  secondary: null,
  critical: null,
  warning: null,
  positive: null,
  neutral: null,
  disabled: null,
  onMedia: null,
  primaryFaded: null,
  neutralFaded: null,

  borderNeutral: null,
  borderNeutralFaded: null,
  borderPrimary: null,
  borderPrimaryFaded: null,
  borderCritical: null,
  borderCriticalFaded: null,
  borderWarning: null,
  borderWarningFaded: null,
  borderPositive: null,
  borderPositiveFaded: null,

  backgroundNeutral: null,
  backgroundNeutralFaded: null,

  backgroundPrimary: null,
  backgroundPrimaryFaded: null,

  backgroundPositive: null,
  backgroundPositiveFaded: null,

  backgroundCritical: null,
  backgroundCriticalFaded: null,

  backgroundDisabled: null,
  backgroundDisabledFaded: null,

  backgroundPage: null,
  backgroundPageFaded: null,

  backgroundBase: null,
  backgroundElevated: null,

  black: null,
  white: null,

  textPrimary: null, // 기본 텍스트 컬러
  textSecondary: null, // 보조 텍스트 컬러
  textOnMedia: null, // 이미지나 미디어 위 텍스트 컬러

  elevationBase: null, // 페이지에 직접 붙은 요소 (예: Card)
  elevationRaised: null, // 약간 떠 있는 요소 (예: Elevated Card)
  elevationOverlay: null, // 가장 상위 오버레이 (예: Modal, Popover)
  elevationOverlayHeavy: null, // 강한 강조 (예: 경고 모달)
  elevationOverlayDimmed: null, // 배경 흐리기 (예: 배경 흐림)
  elevationOverlayBlurred: null, // 블러 효과 오버레이 (예: 풀스크린 모달

  borderDisabled: null, // 비활성화된 테두리
  foregroundDisabled: null, // 비활성화된 텍스트/포그라운드
});

export const { color } = colorThemeContract;
