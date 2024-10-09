import { PropsWithChildren, forwardRef } from 'react';
import {
  buttonSizeVariants,
  buttonColorVariants,
  buttonBackgroundVariants,
  buttonSizeVariantKeyTypes,
  buttonColorVariantKeyTypes,
  buttonBackgroundVariantKeyTypes,
  buttonStyle,
} from './Button.css';

export const Button = forwardRef<
  HTMLButtonElement,
  PropsWithChildren<ButtonProps>
>(function Button(
  {
    size = 'md',
    color = 'white',
    background = 'primary',
    onClick,
    onKeyDown,
    children,
  },
  ref
) {
  return (
    <button
      ref={ref}
      className={`${buttonSizeVariants[size]} ${buttonColorVariants[color]} ${buttonBackgroundVariants[background]} ${buttonStyle}`}
      onClick={onClick}
      onKeyDown={onKeyDown}
    >
      {children}
    </button>
  );
});

interface ButtonProps {
  size?: buttonSizeVariantKeyTypes;
  color?: buttonColorVariantKeyTypes;
  background?: buttonBackgroundVariantKeyTypes;
  onClick?: (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  onKeyDown?: (e: React.KeyboardEvent<HTMLButtonElement>) => void;
  children: React.ReactNode;
}
