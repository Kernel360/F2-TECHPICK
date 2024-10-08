import { style } from '@vanilla-extract/css';

export const bookmarkPageLayout = style({
  width: '360px',
  height: '400px',
  marginBottom: '24px',
  padding: '16px',
});

export const pickFormLayout = style({
  display: 'flex',
  flexDirection: 'column',
  gap: '32px',
});

export const formFieldLayout = style({
  display: 'flex',
  gap: '16px',
  alignItems: 'center',
  width: '100%',
});

export const titleInputStyle = style({
  width: '100%',
  height: '40px',
  border: '1px solid transparent',
  borderRadius: '4px',
  padding: '8px',
  backgroundColor: '#f9f9f9',
  fontSize: '1rem',
  transition: 'border 0.3s ease',

  ':focus': {
    border: '1px solid #444C52',
    outline: 'none',
    backgroundColor: '#ffffff',
  },
});

export const textAreaStyle = style({
  width: '264px',
  height: '72px',
  padding: '8px',
  fontSize: '1rem',
  border: '1px solid transparent',
  borderRadius: '4px',
  backgroundColor: '#f9f9f9',
  resize: 'none',
  transition: 'border 0.3s ease',

  ':focus': {
    border: '1px solid #444C52',
    outline: 'none',
    backgroundColor: '#ffffff',
  },
});

export const submitButtonLayout = style({
  marginLeft: 'auto',
});

export const labelLayout = style({
  width: '48px',
});
