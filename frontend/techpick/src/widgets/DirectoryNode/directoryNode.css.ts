import { commonThemeContract, themeContract } from '@/shared/themes';
import { style } from '@vanilla-extract/css';

export const dirNodeWrapper = style({
  width: '100%',
  lineHeight: '32px',
  ':hover': {
    backgroundColor: '#f0f0f0',
  },
});

export const dirNodeWrapperFocused = style({
  width: '100%',
  lineHeight: '32px',
  backgroundColor: '#e0e7f3',
  ':hover': {
    backgroundColor: '#e2e8f4',
  },
});

export const dirNode = style({
  display: 'flex',
  alignItems: 'center',
  color: themeContract.color.font,
  fontWeight: 300,
  padding: `0 ${commonThemeContract.space.md}`,
});

export const dirIcFolder = style({
  width: '16px',
  height: '16px',
  marginLeft: '16px',
  marginRight: '8px',
});

export const nodeNameInput = style({
  fontSize: '14px',
  fontWeight: 300,
  margin: '4px 0',
  padding: '3px',
  backgroundColor: 'white',
  color: themeContract.color.font,
  outline: 'none',
  border: `1px solid ${themeContract.color.border}`,
  borderRadius: '4px',
});
