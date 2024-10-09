import { PropsWithChildren } from 'react';
import {
  SelectedTagListLayoutStyle,
  ListLayoutHeightVariantKeyTypes,
  ListLayoutHeightVariants,
  SelectedTagListLayoutFocusStyleVarianKeyTypes,
  SelectedTagListLayoutFocusStyleVariant,
} from './SelectedTagListLayout.css';

export function SelectedTagListLayout({
  height = 'flexible',
  focusStyle = 'none',
  children,
}: PropsWithChildren<SelectedTagListLayoutProps>) {
  return (
    <div
      className={`${ListLayoutHeightVariants[height]} ${SelectedTagListLayoutFocusStyleVariant[focusStyle]} ${SelectedTagListLayoutStyle}`}
    >
      {children}
    </div>
  );
}

interface SelectedTagListLayoutProps {
  height?: ListLayoutHeightVariantKeyTypes;
  focusStyle?: SelectedTagListLayoutFocusStyleVarianKeyTypes;
}
