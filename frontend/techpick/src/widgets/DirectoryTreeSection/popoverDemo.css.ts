// directoryTreeSection.css.ts
import { style, createTheme, keyframes } from '@vanilla-extract/css';

// 테마 설정
export const [themeClass, vars] = createTheme({
  colors: {
    violet7: 'var(--violet-7)',
    violet8: 'var(--violet-8)',
    violet11: 'var(--violet-11)',
    violet4: 'var(--violet-4)',
    blackA7: 'var(--black-a7)',
    mauve12: 'var(--mauve-12)',
  },
});

// 애니메이션 정의
const slideUpAndFade = keyframes({
  from: { opacity: 0, transform: 'translateY(2px)' },
  to: { opacity: 1, transform: 'translateY(0)' },
});

const slideRightAndFade = keyframes({
  from: { opacity: 0, transform: 'translateX(-2px)' },
  to: { opacity: 1, transform: 'translateX(0)' },
});

const slideDownAndFade = keyframes({
  from: { opacity: 0, transform: 'translateY(-2px)' },
  to: { opacity: 1, transform: 'translateY(0)' },
});

const slideLeftAndFade = keyframes({
  from: { opacity: 0, transform: 'translateX(2px)' },
  to: { opacity: 1, transform: 'translateX(0)' },
});

// 컴포넌트 스타일 정의
export const popoverContent = style({
  borderRadius: '4px',
  padding: '20px',
  width: '260px',
  backgroundColor: 'white',
  boxShadow: `
    hsl(206 22% 7% / 35%) 0px 10px 38px -10px,
    hsl(206 22% 7% / 20%) 0px 10px 20px -15px
  `,
  animationDuration: '400ms',
  animationTimingFunction: 'cubic-bezier(0.16, 1, 0.3, 1)',
  willChange: 'transform, opacity',
  selectors: {
    '&:focus': {
      boxShadow: `
        hsl(206 22% 7% / 35%) 0px 10px 38px -10px,
        hsl(206 22% 7% / 20%) 0px 10px 20px -15px,
        0 0 0 2px ${vars.colors.violet7}
      `,
    },
    '&[data-state="open"][data-side="top"]': {
      animationName: slideDownAndFade,
    },
    '&[data-state="open"][data-side="right"]': {
      animationName: slideLeftAndFade,
    },
    '&[data-state="open"][data-side="bottom"]': {
      animationName: slideUpAndFade,
    },
    '&[data-state="open"][data-side="left"]': {
      animationName: slideRightAndFade,
    },
  },
});

export const popoverArrow = style({
  fill: 'white',
});

export const popoverClose = style({
  fontFamily: 'inherit',
  borderRadius: '100%',
  height: '25px',
  width: '25px',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  color: vars.colors.violet11,
  position: 'absolute',
  top: '5px',
  right: '5px',
  selectors: {
    '&:hover': {
      backgroundColor: vars.colors.violet4,
    },
    '&:focus': {
      boxShadow: `0 0 0 2px ${vars.colors.violet7}`,
    },
  },
});

export const iconButton = style({
  fontFamily: 'inherit',
  borderRadius: '100%',
  height: '35px',
  width: '35px',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  color: vars.colors.violet11,
  backgroundColor: 'white',
  boxShadow: `0 2px 10px ${vars.colors.blackA7}`,
  selectors: {
    '&:hover': {
      backgroundColor: vars.colors.violet4,
    },
    '&:focus': {
      boxShadow: '0 0 0 2px black',
    },
  },
});

export const fieldset = style({
  display: 'flex',
  gap: '20px',
  alignItems: 'center',
});

export const label = style({
  fontSize: '13px',
  color: vars.colors.violet11,
  width: '75px',
});

export const input = style({
  width: '100%',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  flex: 1,
  borderRadius: '4px',
  padding: '0 10px',
  fontSize: '13px',
  lineHeight: 1,
  color: vars.colors.violet11,
  boxShadow: `0 0 0 1px ${vars.colors.violet7}`,
  height: '25px',
  selectors: {
    '&:focus': {
      boxShadow: `0 0 0 2px ${vars.colors.violet8}`,
    },
  },
});

export const text = style({
  margin: 0,
  color: vars.colors.mauve12,
  fontSize: '15px',
  lineHeight: '19px',
  fontWeight: 500,
});
