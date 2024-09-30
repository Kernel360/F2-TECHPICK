import { style } from '@vanilla-extract/css';
import { commonThemeContract, themeContract } from '@/shared/themes';

export const linkEditorLabel = style({
  display: 'flex',
  gap: commonThemeContract.space.sm,
});

export const searchSection = style({
  display: 'flex',
  gap: commonThemeContract.space.sm,
});

export const linkEditorSection = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
});

export const linkEditorHeader = style({
  width: '100%',
  height: '56px',
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  borderBottom: `1px solid ${themeContract.color.border}`,
  padding: `0 ${commonThemeContract.space.md}`,
});

export const linkEditor = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  flexDirection: 'column',
});

export const folderViewSection = style({
  width: '100%',
  height: '160px',
  display: 'flex',
  backgroundColor: '#f0f0f0',
  padding: `0 ${commonThemeContract.space.md}`,
});

export const folderWrapper = style({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
  padding: `0 ${commonThemeContract.space.md}`,
  fontWeight: 300,
});

export const linkViewSection = style({
  width: '100%',
  height: '100%',
  display: 'flex',
  alignItems: 'start',
  padding: `${commonThemeContract.space.lg} ${commonThemeContract.space.md}`,
});

export const linkWrapper = style({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
  padding: `0 ${commonThemeContract.space.md}`,
  fontWeight: 300,
});

export const linkEditorSectionFooter = style({
  width: '100%',
  height: '56px',
  borderTop: `1px solid ${themeContract.color.border}`,
});

export const directoryTree = style({
  width: '100%',
  height: '100%',
});
