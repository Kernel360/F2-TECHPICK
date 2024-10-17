import { style } from '@vanilla-extract/css';
import { space, color } from 'techpick-shared';

export const pickCardLayout = style({
  display: 'flex',
  flexDirection: 'column',
  alignItems: 'center',
  gap: space.sm,
  width: '280px',
  height: '320px',
  border: `1px solid ${color.border}`,
  borderRadius: '4px',
  backgroundColor: color.background,
});

export const cardImageSectionStyle = style({
  width: '100%',
  height: '64px',
});

export const cardImageStyle = style({
  objectFit: 'cover',
});

export const cardTitleSectionStyle = style({
  width: '264px',
  height: '96px',
  whiteSpace: 'normal',
  wordBreak: 'break-all',
  overflowY: 'scroll',
});

export const cardDescriptionSectionStyle = style({
  width: '264px',
  height: '72px',
  whiteSpace: 'normal',
  wordBreak: 'break-all',
  overflowY: 'scroll',
  fontSize: '14px',
});
