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

export const tagDialogPortal = style({
  position: 'absolute',
  top: '0',
  zIndex: '1',
  backgroundColor: 'white',
});

export const selectedTagItemStyle = style({
  display: 'flex',
  justifyContent: 'space-between',

  // 기본 스타일 (선택된 항목이 아닐 때)
  backgroundColor: 'transparent',
  ':hover': {
    backgroundColor: '#f0f0f0', // 호버 시 색상
  },
  // 선택된 상태일 때
  selectors: {
    '&[data-selected="true"]': {
      backgroundColor: 'green',
    },
    '&[data-disabled="true"]': {
      display: 'none',
    },
  },
});

export const commandInputLayout = style({
  display: 'flex',
  flex: '1 1 60px',
  minWidth: '64px',
  height: '20px',
});

export const commandInputStyle = style({
  outline: 'none',
  border: 'none',
  padding: '0 4px',
  height: '20px',
});
