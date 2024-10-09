import { style } from '@vanilla-extract/css';

export const tagDialogPortalLayout = style({
  position: 'absolute',
  top: '0',
  zIndex: '0',
  backgroundColor: 'white',
});

export const commandInputStyle = style({
  display: 'flex',
  flex: '1 1 60px',
  minWidth: '64px',
  height: '20px',
  outline: 'none',
  border: 'none',
  padding: '0 4px',
});

export const tagListItemStyle = style({
  display: 'flex',
  justifyContent: 'space-between',

  // 기본 스타일 (선택된 항목이 아닐 때)
  backgroundColor: 'transparent',

  // 선택된 상태일 때
  selectors: {
    '&[data-selected="true"]': {
      backgroundColor: '#4CAF50',
    },
    '&[data-disabled="true"]': {
      display: 'none',
    },
  },
});
