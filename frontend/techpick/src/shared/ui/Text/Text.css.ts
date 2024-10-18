import { style, styleVariants } from '@vanilla-extract/css';
import { colorThemeContract, commonThemeContract } from 'techpick-shared';

export const fontSizeVariants = styleVariants({
  xs: { fontSize: '0.75rem' }, // 12
  sm: { fontSize: '0.875rem' }, // 14
  md: { fontSize: '1rem' }, // 16
  lg: { fontSize: '1.125rem' }, // 18
  xl: { fontSize: '1.25rem' }, // 20
  '2xl': { fontSize: '1.5rem' }, // 24
});

export type fontSizeVariantKeyTypes = keyof typeof fontSizeVariants;

const { fontWeight } = commonThemeContract.typography;

export const fontWeightVariants = styleVariants({
  regular: { fontWeight: fontWeight.regular },
  semibold: { fontWeight: fontWeight.semibold },
  bold: { fontWeight: fontWeight.bold },
  extrabold: { fontWeight: fontWeight.extrabold },
});

export type fontWeightVariantKeyTypes = keyof typeof fontWeightVariants;

export const textStyle = style({
  color: colorThemeContract.color.font,
});
