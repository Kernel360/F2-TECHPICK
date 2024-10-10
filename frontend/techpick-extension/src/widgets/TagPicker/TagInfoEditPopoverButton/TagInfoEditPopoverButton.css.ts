import { style } from '@vanilla-extract/css';

export const tagInfoEditPopoverContent = style({
  position: 'relative',
  display: 'flex',
  flexDirection: 'column',
  zIndex: '4',
  border: '1px solid black',
  padding: '8px',
  backgroundColor: 'white',
});

export const popoverOverlayStyle = style({
  position: 'fixed',
  top: 0,
  left: 0,
  width: '100vw',
  height: '100vh',
  zIndex: 2,
  backgroundColor: 'rgba(0, 0, 0, 0)',
});
