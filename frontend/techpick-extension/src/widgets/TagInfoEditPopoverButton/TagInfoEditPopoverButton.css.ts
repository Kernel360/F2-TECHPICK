import { style } from '@vanilla-extract/css';

export const tagInfoEditPopoverTrigger = style({
  position: 'relative',
  width: '20px',
  height: '20px',
  cursor: 'pointer',
});

export const tagInfoEditPopoverContent = style({
  position: 'absolute',
  top: '0',
  left: 0,
  zIndex: '3',
  border: '1px solid black',
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
