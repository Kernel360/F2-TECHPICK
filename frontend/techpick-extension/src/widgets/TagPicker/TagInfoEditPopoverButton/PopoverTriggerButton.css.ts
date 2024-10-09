import { style } from '@vanilla-extract/css';

export const PopoverTriggerButtonStyle = style({
  position: 'relative',
  display: 'flex',
  justifyContent: 'center',
  alignItems: 'center',
  width: '20px',
  height: '20px',
  border: '1px solid transparent',
  backgroundColor: 'transparent',
  cursor: 'pointer',

  ':hover': {
    backgroundColor: '#43A047',
    transition: 'background-color 0.3s ease, box-shadow 0.3s ease',
  },
});
