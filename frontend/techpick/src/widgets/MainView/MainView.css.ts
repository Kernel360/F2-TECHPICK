import { style } from '@vanilla-extract/css';
import {
  commonThemeContract,
  themeContract,
} from '@/shared/themes/themeContract.css';

export const viewWrapper = style({
  width: '100%',
  height: '100%',
  backgroundColor: themeContract.color.background,
  color: themeContract.color.font,
});

export const viewContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  borderRadius: '8px',
});

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
