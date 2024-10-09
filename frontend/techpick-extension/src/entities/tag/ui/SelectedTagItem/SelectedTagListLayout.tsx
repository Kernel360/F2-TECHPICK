import { PropsWithChildren } from 'react';
import {
  SelectedTagListLayoutStyle,
  ListLayoutHeightVariantKeyTypes,
  ListLayoutHeightVariants,
} from './SelectedTagListLayout.css';

export function SelectedTagListLayout({
  height = 'flexible',
  children,
}: PropsWithChildren<SelectedTagListLayoutProps>) {
  return (
    <div
      className={`${ListLayoutHeightVariants[height]} ${SelectedTagListLayoutStyle} `}
    >
      {children}
    </div>
  );
}

interface SelectedTagListLayoutProps {
  height?: ListLayoutHeightVariantKeyTypes;
}
