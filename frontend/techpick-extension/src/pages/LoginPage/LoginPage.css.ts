import { style } from '@vanilla-extract/css';
import { colorThemeContract } from 'techpick-shared';

export const loginPageLayout = style({
  width: '360px',
  height: '320px',
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: '32px',
  paddingTop: '40px',
  backgroundColor: colorThemeContract.color.background,
});
