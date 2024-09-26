import { themeContract } from '@/shared/themes/themeContract.css';
import { commonThemeContract } from '@/shared/themes/themeContract.css';
import { style } from '@vanilla-extract/css';

export const locationContainer = style({
  display: 'flex',
  gap: commonThemeContract.space.sm,
});

export const topButtonContainer = style({
  display: 'flex',
  gap: commonThemeContract.space.sm,
});

export const middleSection = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
});

export const middleHeader = style({
  width: '100%',
  height: '56px',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  borderBottom: `1px solid ${themeContract.color.border}`,
  padding: `0 ${commonThemeContract.space.md}`,
});

export const middleView = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
});

export const folderContainer = style({
  width: '100%',
  height: '200px',
  backgroundColor: '#f0f0f0',
});

export const bookmarkContainer = style({
  width: '100%',
  height: '100%',
  backgroundColor: '#d5c9c9',
});

export const middleFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${themeContract.color.border}`,
});

export const dirTree = style({
  width: '100%',
  height: '100%',
});

export const dirRow = style({});
