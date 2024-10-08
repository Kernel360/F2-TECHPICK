import { styleVariants } from '@vanilla-extract/css';

export const fontSizeVariants = styleVariants({
  xs: { fontSize: '0.75rem' }, // 12
  sm: { fontSize: '0.875rem' }, // 14
  md: { fontSize: '1rem' }, // 16
  lg: { fontSize: '1.125rem' }, // 18
  xl: { fontSize: '1.25rem' }, // 20
  '2xl': { fontSize: '1.5rem' }, // 24
});

export type fontSizeVariantKeyTypes = keyof typeof fontSizeVariants;

export const fontWeightVariants = styleVariants({
  regular: { fontWeight: '400' },
  semibold: { fontWeight: '600' },
  bold: { fontWeight: '700' },
  extrabold: { fontWeight: '800' },
});

export type fontWeightVariantKeyTypes = keyof typeof fontWeightVariants;
