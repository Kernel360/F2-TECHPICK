import { colorThemeContract, commonThemeContract } from 'techpick-shared';
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
