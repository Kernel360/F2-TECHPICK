import { commonThemeContract, themeContract } from '@/shared/themes';
import { style } from '@vanilla-extract/css';

export const dirNodeWrapper = style({
  width: '100%',
  lineHeight: '32px',
  ':hover': {
    backgroundColor: '#f0f0f0',
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
