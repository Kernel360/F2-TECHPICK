import { style } from '@vanilla-extract/css';
import { SelectedTagCommonStyle } from '@/entities/tag';

export const tagDialogPortalLayout = style({
  position: 'absolute',
  top: '0',
  zIndex: '1',
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

export const tagListStyle = style({
  borderRadius: '4px',
  boxShadow:
    'rgba(15, 15, 15, 0.1) 0px 0px 0px 1px, rgba(15, 15, 15, 0.2) 0px 3px 6px, rgba(15, 15, 15, 0.4) 0px 9px 24px',
});

export const tagListItemStyle = style({
  display: 'flex',
  justifyContent: 'space-between',
  alignItems: 'center',
  backgroundColor: 'transparent',
  padding: '0 4px',

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

export const tagListItemContentStyle = style({
  maxWidth: `calc(${SelectedTagCommonStyle.width} - 34px)`, // 26px은 생성 텍스트의 영역 8px는 패딩
  height: '20px',
  lineHeight: '20px',
  fontSize: '14px',
  whiteSpace: 'nowrap', // 줄 바꿈 방지
  overflow: 'hidden', // 넘치는 내용 숨김
  textOverflow: 'ellipsis', // 생략 부호 추가
});

export const tagCreateTextStyle = style({
  width: '26px',
  fontSize: '14px',
});
