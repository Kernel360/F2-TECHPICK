import { style } from '@vanilla-extract/css';
import { themeContract } from '@/shared/themes/themeContract.css';
import { commonThemeContract } from '@/shared/themes/themeContract.css';

export const leftSection = style({
  width: '400px',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
  borderRight: `1px solid ${themeContract.color.border}`,
});

export const leftHeader = style({
  width: '100%',
  height: '56px',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  borderBottom: `1px solid ${themeContract.color.border}`,
  padding: `0 ${commonThemeContract.space.md}`,
});

export const logoContainer = style({
  display: 'flex',
  alignItems: 'center',
});

export const logo = style({
  fontSize: commonThemeContract.typography.fontSize.lg,
  fontWeight: 400,
  paddingLeft: '12px',
});

export const dirContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
  backgroundColor: themeContract.color.background,
});

export const dirHeaderWrapper = style({
  width: '100%',
  height: '28px',
  display: 'flex',
  alignItems: 'center',
  paddingLeft: commonThemeContract.space.lg,
  borderBottom: `1px solid ${themeContract.color.border}`,
});

export const dirHeader = style({
  width: '100%',
  paddingLeft: commonThemeContract.space.xs,
  color: themeContract.color.font,
  fontWeight: 350,
});

export const treeWrapper = style({
  height: '100%',
  overflow: 'scroll',
});

export const leftFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${themeContract.color.border}`,
});

export const dirTree = style({
  width: '100%',
  height: '100%',
});

export const dirRow = style({});
