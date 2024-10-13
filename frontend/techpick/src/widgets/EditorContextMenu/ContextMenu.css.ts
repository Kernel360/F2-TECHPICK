import { style } from '@vanilla-extract/css';
import { themeContract } from '@/shared/themes';

export const ContextMenuTrigger = style({
  display: 'block',
  color: 'white',
  borderRadius: '4px',
  fontSize: '15px',
  userSelect: 'none',
  width: '300px',
  textAlign: 'center',
});

export const ContextMenuContent = style({
  minWidth: '220px',
  backgroundColor: 'white',
  borderRadius: '6px',
  overflow: 'hidden',
  padding: '5px',
  boxShadow:
    '0px 10px 38px -10px rgba(22, 23, 24, 0.35), 0px 10px 20px -15px rgba(22, 23, 24, 0.2)',
});

export const ContextMenuSubContent = ContextMenuContent;

export const ContextMenuItem = style({
  fontSize: '13px',
  lineHeight: '1',
  color: themeContract.color.font,
  borderRadius: '3px',
  display: 'flex',
  alignItems: 'center',
  height: '25px',
  padding: '0 5px',
  position: 'relative',
  paddingLeft: '25px',
  userSelect: 'none',
  outline: 'none',
  ':hover': {
    backgroundColor: '#e2e8f4',
  },
});

export const ContextMenuSubTrigger = ContextMenuItem;

export const ContextMenuSeparator = style({
  height: '1px',
  backgroundColor: 'white',
  margin: '5px',
});

export const RightSlot = style({
  marginLeft: 'auto',
  paddingLeft: '20px',
  color: 'black',
  selectors: {
    '[data-highlighted] &': {
      color: 'white',
    },
    '[data-disabled] &': {
      color: '#c0c0c0',
    },
  },
});
