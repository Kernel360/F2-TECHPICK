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
  width: '400px',
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

export const logoContainer = style({
  display: 'flex',
  alignItems: 'center',
});

export const logo = style({
  fontSize: themeVars.typography.fontSize.lg,
  fontWeight: 400,
  paddingLeft: '12px',
});

export const dirContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
  backgroundColor: themeVars.color.background,
});

export const dirHeaderWrapper = style({
  width: '100%',
  height: '28px',
  display: 'flex',
  alignItems: 'center',
  paddingLeft: themeVars.space.lg,
  borderBottom: `1px solid ${themeVars.color.border}`,
});

export const dirHeader = style({
  width: '100%',
  paddingLeft: themeVars.space.xs,
  color: themeVars.typography.fontColor,
  fontWeight: 350,
});

export const treeWrapper = style({
  flexGrow: 1,
  minBlockSize: 0,
});

export const leftFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${themeVars.color.border}`,
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
  borderTop: `1px solid ${themeVars.color.border}`,
});

export const rightSection = style({
  width: '0%',
  height: '100%',
});

export const dirTree = style({
  width: '100%',
  height: '100%',
});

export const dirRow = style({
  // borderBottom: `1px solid ${themeVars.color.border}`,
  // lineHeight: themeVars.typography.lineHeight.md,
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
  color: themeVars.typography.fontColor,
  fontWeight: 300,
  padding: `0 ${themeVars.space.md}`,
});

export const dirIcFolder = style({
  width: '16px',
  height: '16px',
  marginLeft: '16px',
  marginRight: '8px',
});
