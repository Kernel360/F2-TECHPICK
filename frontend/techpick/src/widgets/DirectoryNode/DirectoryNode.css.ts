import { colorThemeContract, commonThemeContract } from 'techpick-shared';
import { style } from '@vanilla-extract/css';

export const dirNodeWrapper = style({
  width: '100%',
  lineHeight: '32px',
  ':hover': {
    backgroundColor: 'rgba(223,223,223,0.3)',
  },
});

export const dirNodeWrapperFocused = style({
  width: '100%',
  lineHeight: '32px',
  backgroundColor: 'rgba(221,219,214,0.5)',
  ':hover': {
    backgroundColor: 'rgba(221,219,214,0.5)',
  },
});

export const dirNode = style({
  display: 'flex',
  alignItems: 'center',
  color: colorThemeContract.color.font,
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
