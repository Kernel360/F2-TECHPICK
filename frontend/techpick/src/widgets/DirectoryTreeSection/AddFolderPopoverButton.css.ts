// directoryTreeSection.css.ts
import { style, keyframes } from '@vanilla-extract/css';

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
  borderRadius: '8px',
  padding: '20px',
  width: '260px',
  backgroundColor: 'white',
  boxShadow: '1px 1px 10px #c9c9c9',
  animationDuration: '400ms',
  animationTimingFunction: 'cubic-bezier(0.16, 1, 0.3, 1)',
  willChange: 'transform, opacity',
  selectors: {
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

export const popoverClose = style({
  fontFamily: 'inherit',
  borderRadius: '100%',
  border: 'none',
  height: '25px',
  width: '25px',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  color: '#4c1d95',
  backgroundColor: 'white',
  position: 'absolute',
  top: '5px',
  right: '5px',
  selectors: {
    '&:hover': {
      backgroundColor: '#ede9fe',
    },
  },
});

export const iconButton = style({
  fontFamily: 'inherit',
  border: 'none',
  width: '28px',
  height: '24px',
  borderRadius: '4px',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  color: '#4c1d95',
  backgroundColor: 'white',
  cursor: 'pointer',
  outline: 'none',
  selectors: {
    '&:hover': {
      backgroundColor: '#ede9fe',
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
  color: '#4c1d95',
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
  color: '#4c1d95',
  height: '25px',
  border: '1px solid #e5e5e5',
  ':focus': {
    outline: '1px solid #7c3aed',
  },
});

export const text = style({
  marginBottom: '8px',
  color: '#3f3f46',
  fontSize: '15px',
  lineHeight: '19px',
  fontWeight: 500,
});
