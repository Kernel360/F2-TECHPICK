import { style } from '@vanilla-extract/css';

export const dialogContentStyle = style({
  position: 'absolute',
  margin: 'auto',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  border: '1px solid black',
  backgroundColor: 'white',
  width: '200px',
  height: '200px',
});

export const dialogOverlayStyle = style({
  backgroundColor: 'rgba(0,0,0,0)',
  position: 'fixed',
  inset: 0,
});
