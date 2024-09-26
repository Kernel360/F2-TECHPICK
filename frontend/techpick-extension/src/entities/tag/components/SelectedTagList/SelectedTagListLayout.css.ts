import { style } from '@vanilla-extract/css';

export const SelectedTagListLayoutStyle = style({
  display: 'flex',
  gap: '4px',
  flexWrap: 'wrap', // 줄 바꿈을 허용
  overflow: 'visible',
  padding: '4px',
  width: '200px',
});
