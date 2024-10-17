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

export const logout = style({
  cursor: 'pointer',
});

export const directoryTreeContainer = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  position: 'relative',
  flexDirection: 'column',
  backgroundColor: colorThemeContract.color.background,
});

export const unClassifiedLabelContainer = style({
  width: '100%',
  height: '28px',
  display: 'flex',
  alignItems: 'center',
  paddingLeft: commonThemeContract.space.lg,
  borderBottom: `1px solid ${colorThemeContract.color.border}`,
  cursor: 'pointer',
});

export const rightIcon = style({
  marginRight: commonThemeContract.space.sm,
});

export const directoryLabelContainer = style({
  width: '100%',
  height: '28px',
  display: 'flex',
  alignItems: 'center',
  paddingLeft: commonThemeContract.space.lg,
  borderBottom: `1px solid ${colorThemeContract.color.border}`,
});

export const recycleBinContainerClosed = style({
  display: 'flex',
  flexDirection: 'column',
  marginTop: 'auto',
  marginBottom: '28px',
  transition: 'transform 0.3s ease-out, height 0.3s ease-out',
  height: '28px',
  transform: 'translateY(100%)',
  overflow: 'hidden',
  position: 'absolute',
  bottom: '0',
});
export const recycleBinContainerOpen = style({
  height: 'calc(50%)',
  transform: 'translateY(0)',
  transition: 'transform 0.3s ease-out',
  position: 'absolute',
  bottom: '0',
});

export const recycleBinLabelContainer = style({
  width: '100%',
  height: '28px',
  display: 'flex',
  alignItems: 'center',
  paddingLeft: commonThemeContract.space.lg,
  borderTop: `1px solid ${colorThemeContract.color.border}`,
  cursor: 'pointer',
});

export const directoryLabel = style({
  width: '100%',
  paddingLeft: commonThemeContract.space.sm,
  color: colorThemeContract.color.font,
  fontWeight: 350,
});

export const plusButton = style({
  cursor: 'pointer',
  marginRight: commonThemeContract.space.sm,
  zIndex: 100,
});

export const directoryTreeWrapper = style({
  outline: 'none',
  height: 'calc(50% - 64px)',
  overflow: 'hidden',
  transition: 'height 0.3s',
});

export const directoryTreeWrapperFullSize = style({
  height: 'calc(100% - 84px)',
  overflow: 'hidden',
  transition: 'height 0.3s',
});

export const recycleBinTreeWrapper = style({
  outline: 'none',
  height: 'calc(100% - 32px)',
  overflow: 'hidden',
  transition: 'height 0.4s',
  borderTop: `1px solid ${colorThemeContract.color.border}`,
});

export const recycleBinTreeWrapperClosed = style({
  height: '0',
  overflow: 'hidden',
});

export const directoryTreeSectionFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${colorThemeContract.color.border}`,
});

export const directoryTree = style({
  width: '100%',
  height: '100%',
  outline: 'none',
});
