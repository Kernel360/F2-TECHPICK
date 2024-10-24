import { createTheme } from '@vanilla-extract/css';

export const [commonThemeClass, commonTheme] = createTheme({
  space: {
    px: '1px',
    '4': '0.5rem', // 4px -> 0.5rem
    '8': '1rem', // 8px -> 1rem
    '12': '1.5rem', // 12px -> 1.5rem
    '16': '2rem', // 16px -> 2rem
    '20': '2.5rem', // 20px -> 2.5rem
    '24': '3rem', // 24px -> 3rem
    '28': '3.5rem', // 28px -> 3.5rem
    '32': '4rem', // 32px -> 4rem
    '36': '4.5rem', // 36px -> 4.5rem
    '40': '5rem', // 40px -> 5rem
    '44': '5.5rem', // 44px -> 5.5rem
    '48': '6rem', // 48px -> 6rem
    '52': '6.5rem', // 52px -> 6.5rem
    '56': '7rem', // 56px -> 7rem
    '64': '8rem', // 64px -> 8rem
    '72': '9rem', // 72px -> 9rem
    '80': '10rem', // 80px -> 10rem
    '96': '12rem', // 96px -> 12rem
    '112': '14rem', // 112px -> 14rem
    '128': '16rem', // 128px -> 16rem
    '144': '18rem', // 144px -> 18rem
    '160': '20rem', // 160px -> 20rem
    '176': '22rem', // 176px -> 22rem
    '192': '24rem', // 192px -> 24rem
    '208': '26rem', // 208px -> 26rem
    '224': '28rem', // 224px -> 28rem
    '240': '30rem', // 240px -> 30rem
    '256': '32rem', // 256px -> 32rem
    '272': '34rem', // 272px -> 34rem
    '288': '36rem', // 288px -> 36rem
    '304': '38rem', // 304px -> 38rem
    '320': '40rem', // 320px -> 40rem
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
    '3xs': '14rem', // 14rem -> 224px
    '2xs': '16rem', // 16rem -> 256px
    xs: '20rem', // 20rem -> 320px
    sm: '24rem', // 24rem -> 384px
    md: '28rem', // 28rem -> 448px
    lg: '32rem', // 32rem -> 512px
    xl: '36rem', // 36rem -> 576px
    '2xl': '42rem', // 42rem -> 672px
    '3xl': '48rem', // 48rem -> 768px
    '4xl': '56rem', // 56rem -> 896px
    '5xl': '64rem', // 64rem -> 1024px
    '6xl': '72rem', // 72rem -> 1152px
    '7xl': '80rem', // 80rem -> 1280px
    '8xl': '90rem', // 90rem -> 1440px
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
