import { style } from '@vanilla-extract/css';
import { SelectedTagCommonStyle } from './SelectedTagCommonStyle.css';

export const selectedTagLayoutStyle = style({
  display: 'inline-flex',
  alignItems: 'center',
  maxWidth: `calc(${SelectedTagCommonStyle.width} - 20px)`, // 20px은 버튼의 영역
  height: '20px',
  backgroundColor: 'silver',
});
