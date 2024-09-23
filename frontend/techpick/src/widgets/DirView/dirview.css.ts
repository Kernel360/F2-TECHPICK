import { style } from '@vanilla-extract/css';
import { themeVars } from '@/lib/themes/themeContract.css';

export const viewWrapper = style({
  width: '100%',
  height: '100%',
  backgroundColor: themeVars.color.background,
  color: themeVars.typography.fontColor,
});

export const viewContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  borderRadius: '8px',
});

export const leftSection = style({
  width: '30%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
  borderRight: `1px solid ${themeVars.color.border}`,
});

export const leftHeader = style({
  width: '100%',
  height: '56px',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  borderBottom: `1px solid ${themeVars.color.border}`,
  padding: `0 ${themeVars.space.md}`,
});

export const dirContainer = style({
  width: '100%',
  height: '100%',
  backgroundColor: themeVars.color.background,
});

export const leftFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${themeVars.color.border}`,
});

export const middleSection = style({
  width: '70%',
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
  borderBottom: `1px solid ${themeVars.color.border}`,
  padding: `0 ${themeVars.space.md}`,
});

export const locationContainer = style({
  display: 'flex',
  gap: themeVars.space.sm,
});

export const topButtonContainer = style({
  display: 'flex',
  gap: themeVars.space.sm,
});

export const bookmarkContainer = style({
  width: '100%',
  height: '100%',
});

export const middleFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${themeVars.color.border}`,
});

export const rightSection = style({
  width: '0%',
  height: '100%',
});
