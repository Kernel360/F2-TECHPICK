import { style } from '@vanilla-extract/css';

export const rootLayout = style({
  width: '100%',
  height: '100vh',
});

import { themeContract } from '@/shared/themes';

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
