import { style } from '@vanilla-extract/css';

export const selectedTagLayoutStyle = style({
  display: 'inline-flex',
  alignItems: 'center',
  maxWidth: 'calc(200px - 20px)', // maxWidth는 SelectedListLayout과 같아야함.
  height: '20px',
  backgroundColor: 'silver',
});
