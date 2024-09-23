import { style } from '@vanilla-extract/css';
import { themeVars } from '@/lib/themes/themeContract.css';

export const viewWrapper = style({
  width: '100%',
  height: '100%',
  border: `1px solid red`,
  backgroundColor: themeVars.color.background,
});

export const viewContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  border: `1px solid blue`,
  borderRadius: '8px',
});

export const leftSection = style({
  width: '30%',
  height: '100%',
  border: `1px solid red`,
});
export const middleSection = style({
  width: '70%',
  height: '100%',
  border: `1px solid green`,
});
export const rightSection = style({
  width: '0%',
  height: '100%',
  border: `1px solid blue`,
});
