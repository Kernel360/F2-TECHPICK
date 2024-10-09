import { style } from '@vanilla-extract/css';
import { SelectedTagCommonStyle } from '@/entities/tag';

export const tagPickerLayout = style({
  position: 'relative',
});

export const tagDialogTriggerLayout = style({
  position: 'relative',
  boxSizing: 'border-box',
  cursor: 'pointer',
  width: SelectedTagCommonStyle.width,
  height: '60px',
  border: '1px solid transparent',
  borderRadius: '4px',
  backgroundColor: '#f9f9f9',
  transition: 'border 0.3s ease',

  ':focus': {
    border: '1px solid #444C52',
    outline: 'none',
    backgroundColor: '#ffffff',
  },
});
