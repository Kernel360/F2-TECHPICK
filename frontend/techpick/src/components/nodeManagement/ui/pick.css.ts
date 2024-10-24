import { style } from '@vanilla-extract/css';
import { commonTheme } from 'techpick-shared';

export const linkWrapper = style({
  display: 'flex',
  flexDirection: 'column',
  justifyContent: 'center',
  alignItems: 'center',
  width: '100px',
  height: '100px',
  padding: `0 ${commonTheme.space[16]}`,
  fontWeight: 300,
  textAlign: 'center',
  borderRadius: '4px',
  ':hover': {
    backgroundColor: 'rgba(223,223,223,0.3)',
  },
});
