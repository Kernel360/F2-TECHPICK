import { forwardRef } from 'react';
import * as Popover from '@radix-ui/react-popover';
import { Ellipsis } from 'lucide-react';
import { PopoverTriggerButtonStyle } from './PopoverTriggerButton.css';

export const PopoverTriggerButton = forwardRef(
  function PopoverTriggerButtonWithRef(
    { openPopover }: PopoverTriggerButtonProps,
    ref?: React.Ref<HTMLButtonElement>
  ) {
    return (
      <Popover.Trigger asChild>
        <button
          className={PopoverTriggerButtonStyle}
          ref={ref}
          onKeyDown={(e) => {
            if (e.key === 'Enter') {
              openPopover();
            }
          }}
        >
          <Ellipsis size={14} />
        </button>
      </Popover.Trigger>
    );
  }
);

interface PopoverTriggerButtonProps {
  openPopover: () => void;
}
