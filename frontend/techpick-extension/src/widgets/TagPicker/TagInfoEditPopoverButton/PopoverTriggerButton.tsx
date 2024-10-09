import { forwardRef } from 'react';
import * as Popover from '@radix-ui/react-popover';
import { Ellipsis } from 'lucide-react';
import { PopoverTriggerButtonStyle } from './PopoverTriggerButton.css';

export const PopoverTriggerButton = forwardRef(
  function PopoverTriggerButtonWithRef(
    _props,
    ref?: React.Ref<HTMLDivElement>
  ) {
    return (
      <Popover.Trigger asChild>
        <div
          className={PopoverTriggerButtonStyle}
          ref={ref}
          role="button"
          onClick={(e) => {
            e.stopPropagation();
          }}
        >
          <Ellipsis size={14} />
        </div>
      </Popover.Trigger>
    );
  }
);
