import { useRef, useState } from 'react';
import * as Popover from '@radix-ui/react-popover';
import * as VisuallyHidden from '@radix-ui/react-visually-hidden';
import sanitizeHtml from 'sanitize-html';
import { tagTypes, useTagStore } from '@/entities/tag';
import { ShowDeleteTagDialogButton } from '@/features/tag';
import { PopoverTriggerButton } from './PopoverTriggerButton';
import { PopoverOverlay } from './PopoverOverlay';
import { isEmptyString, isSameValue } from './TagInfoEditPopoverButton.lib';
import { tagInfoEditPopoverContent } from './TagInfoEditPopoverButton.css';

export function TagInfoEditPopoverButton({
  tag,
}: TagInfoEditPopoverButtonProps) {
  const tagInfoEditPopoverButtonRef = useRef<HTMLDivElement | null>(null);
  const tagNameInputRef = useRef<HTMLInputElement | null>(null);
  const [isPopoverOpen, setIsPopoverOpen] = useState(false);
  const { updateTag, updateSelectedTagList } = useTagStore();

  const closePopover = () => {
    setIsPopoverOpen(false);
  };

  // radix-ui popover는 space key를 이용해 popover를 열고 닫습니다.
  // 따라서 space key를 입력 시 값을 더하는 식으로 우회했습니다.
  const handleInputKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === ' ' && tagNameInputRef?.current) {
      tagNameInputRef.current.value += ' ';
      e.preventDefault();
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!tagNameInputRef?.current) {
      return;
    }

    const newTagName = sanitizeHtml(tagNameInputRef.current.value.trim());

    if (isEmptyString(newTagName) || isSameValue(newTagName, tag.name)) {
      closePopover();
      return;
    }

    try {
      await updateTag({ id: tag.id, name: newTagName });
      updateSelectedTagList({ id: tag.id, name: newTagName });
      closePopover();
    } catch (error) {
      if (error instanceof Error) {
        // TODO:  Toast 알림
      }
    }
  };

  return (
    <Popover.Root open={isPopoverOpen} onOpenChange={setIsPopoverOpen}>
      <PopoverTriggerButton ref={tagInfoEditPopoverButtonRef} />
      {isPopoverOpen && <PopoverOverlay onClick={closePopover} />}
      <Popover.Portal container={tagInfoEditPopoverButtonRef.current}>
        <Popover.Content
          className={tagInfoEditPopoverContent}
          // 아래의 stopPropagation은 Enter시에 Popover 창이 닫히는 걸 막습니다.
          onKeyDown={(e) => e.stopPropagation()}
          onClick={(e) => e.stopPropagation()}
        >
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              defaultValue={tag.name}
              ref={tagNameInputRef}
              autoFocus
              onKeyDown={handleInputKeyDown}
            />
            <ShowDeleteTagDialogButton tag={tag} onClick={closePopover} />

            <VisuallyHidden.Root>
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

interface TagInfoEditPopoverButtonProps {
  tag: tagTypes.TagType;
}
