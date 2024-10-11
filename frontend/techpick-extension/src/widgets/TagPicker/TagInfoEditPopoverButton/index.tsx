import { useRef, useState } from 'react';
import { useFloating, shift } from '@floating-ui/react';
import * as VisuallyHidden from '@radix-ui/react-visually-hidden';
import sanitizeHtml from 'sanitize-html';
import { tagTypes, useTagStore } from '@/entities/tag';
import { ShowDeleteTagDialogButton } from '@/features/tag';
import { PopoverTriggerButton } from './PopoverTriggerButton';
import { PopoverOverlay } from './PopoverOverlay';
import { isEmptyString, isSameValue } from './TagInfoEditPopoverButton.lib';
import { tagInfoEditFormLayout } from './TagInfoEditPopoverButton.css';

export function TagInfoEditPopoverButton({
  tag,
}: TagInfoEditPopoverButtonProps) {
  const tagNameInputRef = useRef<HTMLInputElement | null>(null);
  const [isPopoverOpen, setIsPopoverOpen] = useState(false);
  const { updateTag } = useTagStore();
  const { refs, floatingStyles } = useFloating({
    open: isPopoverOpen,
    middleware: [shift({ padding: 4 })],
  });

  const closePopover = () => {
    console.log('closePopover work');
    setIsPopoverOpen(false);
  };

  const handleInputKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === ' ' && tagNameInputRef.current) {
      tagNameInputRef.current.value += ' ';
      e.preventDefault();
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    console.log('handleSubmit work');

    event.preventDefault();

    if (!tagNameInputRef.current) {
      return;
    }

    const newTagName = sanitizeHtml(tagNameInputRef.current.value.trim());

    if (isEmptyString(newTagName) || isSameValue(newTagName, tag.tagName)) {
      closePopover();
      return;
    }

    try {
      await updateTag({
        tagId: tag.tagId,
        tagName: newTagName,
        colorNumber: tag.colorNumber,
        tagOrder: tag.tagOrder,
      });
      closePopover();
    } catch (error) {
      if (error instanceof Error) {
        // TODO: Toast 알림
      }
    }
  };

  return (
    <>
      <PopoverTriggerButton
        ref={refs.setReference}
        onClick={(e) => {
          e.stopPropagation(); // 옵션 버튼을 눌렀을 때, 해당 태그를 선택하는 onSelect를 막기 위헤서 전파 방지
          setIsPopoverOpen(true);
        }}
      />
      {isPopoverOpen && (
        <>
          <PopoverOverlay
            onClick={(e) => {
              closePopover();
              e.stopPropagation();
            }}
          />
          <form
            onSubmit={handleSubmit}
            className={tagInfoEditFormLayout}
            ref={refs.setFloating}
            style={floatingStyles}
            onClick={(e) => e.stopPropagation()}
            onKeyDown={(e) => e.stopPropagation()}
          >
            <input
              type="text"
              defaultValue={tag.tagName}
              ref={tagNameInputRef}
              autoFocus
              onKeyDown={handleInputKeyDown}
            />
            <ShowDeleteTagDialogButton
              tag={tag}
              onClick={() => {
                console.log('ShowDeleteTagDialog wor');
                closePopover();
              }}
            />
            <VisuallyHidden.Root>
              <button type="submit" aria-label="제출">
                제출
              </button>
            </VisuallyHidden.Root>
          </form>
        </>
      )}
    </>
  );
}

interface TagInfoEditPopoverButtonProps {
  tag: tagTypes.TagType;
}
