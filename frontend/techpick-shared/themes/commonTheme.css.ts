import { createTheme } from '@vanilla-extract/css';

export const [commonThemeClass, commonTheme] = createTheme({
  space: {
    px: '1px',
    '0.5': '4px', // 0.5 rem -> 4px
    '1': '8px', // 1 rem -> 8px
    '1.5': '12px', // 1.5 rem -> 12px
    '2': '16px', // 2 rem -> 16px
    '2.5': '20px', // 2.5 rem -> 20px
    '3': '24px', // 3 rem -> 24px
    '3.5': '28px', // 3.5 rem -> 28px
    '4': '32px', // 4 rem -> 32px
    '5': '36px', // 5 rem -> 36px
    '6': '40px', // 6 rem -> 40px
    '7': '44px', // 7 rem -> 44px
    '8': '48px', // 8 rem -> 48px
    '9': '52px', // 9 rem -> 52px
    '10': '56px', // 10 rem -> 56px
    '12': '64px', // 12 rem -> 64px
    '14': '72px', // 14 rem -> 72px
    '16': '80px', // 16 rem -> 80px
    '20': '96px', // 20 rem -> 96px
    '24': '112px', // 24 rem -> 112px
    '28': '128px', // 28 rem -> 128px
    '32': '144px', // 32 rem -> 144px
    '36': '160px', // 36 rem -> 160px
    '40': '176px', // 40 rem -> 176px
    '44': '192px', // 44 rem -> 192px
    '48': '208px', // 48 rem -> 208px
    '52': '224px', // 52 rem -> 224px
    '56': '240px', // 56 rem -> 240px
    '60': '256px', // 60 rem -> 256px
    '64': '272px', // 64 rem -> 272px
    '72': '288px', // 72 rem -> 288px
    '80': '304px', // 80 rem -> 304px
    '96': '320px', // 96 rem -> 320px
  },
  typography: {
    fontColor: '#fff',
    fontSize: {
      xs: '0.75rem', // 12px
      sm: '0.875rem', // 14px
      md: '1rem', // 16px
      lg: '1.125rem', // 18px
      xl: '1.25rem', // 20px
      '2xl': '1.5rem', // 24px
      '3xl': '1.875rem', // 30px
      '4xl': '2.25rem', // 36px
      '5xl': '3rem', // 48px
      '6xl': '3.75rem', // 60px
      '7xl': '4.5rem', // 72px
      '8xl': '6rem', // 96px
      '9xl': '8rem', // 128px
    },
    lineHeight: {
      xs: '1rem', // 16px
      sm: '1.25rem', // 20px
      md: '1.5rem', // 24px
      lg: '1.75rem', // 28px
      xl: '2rem', // 32px
    },
    fontWeights: {
      hairline: '100',
      thin: '200',
      light: '300',
      normal: '400',
      medium: '500',
      semibold: '600',
      bold: '700',
      extrabold: '800',
      black: '900',
    },
  },
  breakpoints: {
    mobile: '576px',
    tablet: '768px',
    desktop: '1024px',
  },
  sizes: {
    max: 'max-content',
    min: 'min-content',
    full: '100%',
    '3xs': '14rem',
    '2xs': '16rem',
    xs: '20rem',
    sm: '24rem',
    md: '28rem',
    lg: '32rem',
    xl: '36rem',
    '2xl': '42rem',
    '3xl': '48rem',
    '4xl': '56rem',
    '5xl': '64rem',
    '6xl': '72rem',
    '7xl': '80rem',
    '8xl': '90rem',
    container: {
      sm: '640px',
      md: '768px',
      lg: '1024px',
      xl: '1280px',
    },
  },
  borderRadius: {
    none: '0',
    sm: '0.125rem',
    base: '0.25rem',
    md: '0.375rem',
    lg: '0.5rem',
    xl: '0.75rem',
    '2xl': '1rem',
    '3xl': '1.5rem',
    full: '9999px',
  },
  zIndex: {
    levelMinus1: ' -1', // 사용되지 않는 요소
    level0: 'auto', // 사용되지 않는 요소
    level1: '0', // 기본 요소
    level2: '10', // 도킹된 요소
    level3: '1000', // 드롭다운
    level4: '1100', // 스티키 요소
    level5: '1200', // 배너
    level6: '1300', // 오버레이
    level7: '1400', // 모달
    level8: '1500', // 팝오버
    level9: '1600', // 스킵 링크
    level10: '1700', // 토스트
    level11: '1800', // 툴팁
  },
});

export const { borderRadius, breakpoints, sizes, space, typography, zIndex } =
  commonTheme;
export const { fontColor, fontSize, fontWeights, lineHeight } = typography;
