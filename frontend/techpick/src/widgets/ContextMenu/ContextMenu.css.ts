import { style } from '@vanilla-extract/css';
import { colorThemeContract } from 'techpick-shared';

export const ContextMenuTrigger = style({
  // display: 'block',
  // color: 'white',
  // borderRadius: '4px',
  // fontSize: '15px',
  // userSelect: 'none',
  // width: '300px',
  // textAlign: 'center',
});

export const ContextMenuContent = style({
  minWidth: '190px',
  backgroundColor: colorThemeContract.color.background,
  borderRadius: '6px',
  overflow: 'hidden',
  padding: '5px',
  boxShadow: '1px 1px 10px rgba(160,160,160,0.5)',
});

export const ContextMenuSubContent = ContextMenuContent;

export const ContextMenuItem = style({
  fontSize: '13px',
  lineHeight: '1',
  color: colorThemeContract.color.font,
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
    backgroundColor: 'rgba(221,219,214,0.5)',
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
