import { style } from '@vanilla-extract/css';

export const selectedTagContentStyle = style({
  padding: '0 4px',
  maxWidth: '100%', // 최대 너비를 부모에 맞춤
  height: '14px',
  fontSize: '14px',
  whiteSpace: 'nowrap', // 줄 바꿈 방지
  overflow: 'hidden', // 넘치는 내용 숨김
  textOverflow: 'ellipsis', // 생략 부호 추가
});
