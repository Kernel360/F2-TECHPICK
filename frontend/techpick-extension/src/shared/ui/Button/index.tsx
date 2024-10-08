import { PropsWithChildren } from 'react';
import {
  buttonSizeVariants,
  buttonColorVariants,
  buttonBackgroundVariants,
  buttonSizeVariantKeyTypes,
  buttonColorVariantKeyTypes,
  buttonBackgroundVariantKeyTypes,
  buttonStyle,
} from './Button.css';

export function Button({
  size = 'md',
  color = 'white',
  background = 'primary',
  onClick,
  onKeyDown,
  children,
}: PropsWithChildren<ButtonProps>) {
  return (
    <button
      className={`${buttonSizeVariants[size]} ${buttonColorVariants[color]} ${buttonBackgroundVariants[background]} ${buttonStyle}`}
      onClick={onClick}
      onKeyDown={onKeyDown}
    >
      {children}
    </button>
  );
}

interface ButtonProps {
  size?: buttonSizeVariantKeyTypes;
  color?: buttonColorVariantKeyTypes;
  background?: buttonBackgroundVariantKeyTypes;
  onClick?: (e: React.MouseEvent<HTMLButtonElement, MouseEvent>) => void;
  onKeyDown?: (e: React.KeyboardEvent<HTMLButtonElement>) => void;
  children: React.ReactNode;
}
