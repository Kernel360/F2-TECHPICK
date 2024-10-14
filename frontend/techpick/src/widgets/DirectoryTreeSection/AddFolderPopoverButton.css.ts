// directoryTreeSection.css.ts
import { style, keyframes } from '@vanilla-extract/css';
import { themeContract } from '@/shared/themes';

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

export const popoverContent = style({
  borderRadius: '4px',
  padding: '20px',
  width: '260px',
  backgroundColor: themeContract.color.background,
  boxShadow: '1px 1px 10px rgba(180,180,180,0.5)',
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
  color: themeContract.color.font,
  backgroundColor: themeContract.color.background,
  position: 'absolute',
  top: '5px',
  right: '5px',
  selectors: {
    '&:hover': {
      backgroundColor: 'rgba(0,0,0,0.06)',
    },
  },
});

export const iconButton = style({
  padding: '0',
  fontFamily: 'inherit',
  border: 'none',
  borderRadius: '4px',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  color: themeContract.color.font,
  backgroundColor: 'transparent',
  cursor: 'pointer',
  outline: 'none',
  selectors: {
    '&:hover': {
      backgroundColor: 'rgba(223,223,223,0.3)',
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
  color: themeContract.color.font,
  width: '75px',
  fontWeight: 300,
});

export const input = style({
  all: 'unset',
  width: '100%',
  display: 'inline-flex',
  alignItems: 'center',
  justifyContent: 'center',
  flex: 1,
  borderRadius: '4px',
  padding: '0 10px',
  fontSize: '13px',
  lineHeight: 1,
  color: themeContract.color.font,
  backgroundColor: themeContract.color.background,
  height: '25px',
  border: `1px solid ${themeContract.color.border}`,

  fontWeight: '350 ',
  ':focus': {
    outline: `1px solid ${themeContract.color.border}`,
  },
});

export const text = style({
  marginBottom: '8px',
  color: themeContract.color.font,
  fontSize: '15px',
  lineHeight: '19px',
  fontWeight: 500,
});
