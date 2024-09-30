import { style } from '@vanilla-extract/css';

export const selectedTagContentStyle = style({
  background: 'silver',
  borderRadius: '4px',
  padding: '0 4px',
  fontSize: '14px',
  height: '14px',
  whiteSpace: 'nowrap', // 줄 바꿈 방지
  overflow: 'hidden', // 넘치는 내용 숨김
  textOverflow: 'ellipsis', // 생략 부호 추가
  maxWidth: '100%', // 최대 너비를 부모에 맞춤
});
