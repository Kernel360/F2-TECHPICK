import { style } from '@vanilla-extract/css';

export const tagInputStyle = style({
  position: 'relative',
});

export const tagDialogTrigger = style({
  cursor: 'pointer',
  maxWidth: '200px',
  minHeight: '20px',
  border: '1px solid black',
  position: 'relative', // 부모 요소를 기준으로 절대 위치 설정 가능
});
