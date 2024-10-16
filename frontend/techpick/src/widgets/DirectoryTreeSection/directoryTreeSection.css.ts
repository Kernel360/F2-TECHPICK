import { colorThemeContract, commonThemeContract } from 'techpick-shared';
import { style } from '@vanilla-extract/css';

export const leftSidebarSection = style({
  width: '400px',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
  borderRight: `1px solid ${colorThemeContract.color.border}`,
});

export const profileSection = style({
  width: '100%',
  height: '56px',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  borderBottom: `1px solid ${colorThemeContract.color.border}`,
  padding: `0 ${commonThemeContract.space.md}`,
});

export const profileContainer = style({
  display: 'flex',
  alignItems: 'center',
});

export const logo = style({
  fontSize: commonThemeContract.typography.fontSize.lg,
  fontWeight: 400,
  paddingLeft: '12px',
});

export const directoryTreeContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
  backgroundColor: colorThemeContract.color.background,
});

export const directoryLabelContainer = style({
  width: '100%',
  height: '28px',
  display: 'flex',
  alignItems: 'center',
  paddingLeft: commonThemeContract.space.lg,
  borderBottom: `1px solid ${colorThemeContract.color.border}`,
});

export const directoryLabel = style({
  width: '100%',
  paddingLeft: commonThemeContract.space.sm,
  color: colorThemeContract.color.font,
  fontWeight: 350,
});

export const directoryTreeWrapper = style({
  height: '100%',
});

export const directoryTreeSectionFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${colorThemeContract.color.border}`,
});

export const directoryTree = style({
  width: '100%',
  height: '100%',
});
