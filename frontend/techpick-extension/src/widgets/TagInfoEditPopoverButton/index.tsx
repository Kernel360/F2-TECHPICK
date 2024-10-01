import { useRef, useState } from 'react';
import { Ellipsis } from 'lucide-react';
import * as Popover from '@radix-ui/react-popover';
import * as VisuallyHidden from '@radix-ui/react-visually-hidden';
import { PopoverOverlay } from './PopoverOverlay';
import {
  tagInfoEditPopoverTrigger,
  tagInfoEditPopoverContent,
} from './TagInfoEditPopoverButton.css';

export function TagInfoEditPopoverButton() {
  const tagInfoEditPopoverButtonRef = useRef<HTMLButtonElement | null>(null);
  const [isPopoverOpen, setIsPopoverOpen] = useState(false);

  const closePopover = () => {
    setIsPopoverOpen(false);
  };

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault(); // 기본 제출 동작 방지
    console.log('제출된 값:');
  };

  return (
    <Popover.Root open={isPopoverOpen} onOpenChange={setIsPopoverOpen}>
      <Popover.Trigger asChild>
        <button
          className={tagInfoEditPopoverTrigger}
          ref={tagInfoEditPopoverButtonRef}
          onClick={(e) => {
            e.stopPropagation();
          }}
          onKeyDown={(e) => {
            e.stopPropagation();
          }}
        >
          <Ellipsis size={14} />
        </button>
      </Popover.Trigger>
      {isPopoverOpen && <PopoverOverlay onClick={closePopover} />}
      <Popover.Portal container={tagInfoEditPopoverButtonRef.current}>
        <Popover.Content
          className={tagInfoEditPopoverContent}
          onClick={(e) => {
            e.stopPropagation();
          }}
          onKeyDown={(e) => {
            // TODO: enter시 변경
            if (e.key === 'Enter') {
              closePopover();
            }

            e.stopPropagation();
          }}
        >
          <form onSubmit={handleSubmit}>
            <input type="text" value={'기존의 값'} />
            <button type="button">삭제</button>
            <VisuallyHidden.Root asChild>
              <button type="submit" aria-label="제출">
                제출
              </button>
            </VisuallyHidden.Root>
          </form>
          <Popover.Close aria-label="Close"></Popover.Close>
        </Popover.Content>
      </Popover.Portal>
    </Popover.Root>
  );
}
