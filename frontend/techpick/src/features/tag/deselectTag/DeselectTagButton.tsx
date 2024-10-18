'use client';

import { X } from 'lucide-react';
import { DeselectTagButtonStyle } from './DeselectTagButton.css';

export function DeselectTagButton({
  onClick = () => {},
}: DeselectTagButtonProps) {
  return (
    <button
      type="button"
      className={DeselectTagButtonStyle}
      onClick={() => {
        onClick();
      }}
    >
      <X size={8} />
    </button>
  );
}

interface DeselectTagButtonProps {
  onClick?: () => void;
}
