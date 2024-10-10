import { useRef, useState } from 'react';
import { useFloating, shift } from '@floating-ui/react';
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
  const tagNameInputRef = useRef<HTMLInputElement | null>(null);
  const [isPopoverOpen, setIsPopoverOpen] = useState(false);
  const { updateTag, updateSelectedTagList } = useTagStore();
  const { refs, floatingStyles } = useFloating({
    open: isPopoverOpen,
    middleware: [shift({ padding: 4 })],
  });

  const closePopover = () => {
    setIsPopoverOpen(false);
  };

  const handleInputKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === ' ' && tagNameInputRef.current) {
      tagNameInputRef.current.value += ' ';
      e.preventDefault();
    }
  };

  const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!tagNameInputRef.current) {
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
            className={tagInfoEditPopoverContent}
            ref={refs.setFloating}
            style={floatingStyles}
            onClick={(e) => e.stopPropagation()}
            onKeyDown={(e) => e.stopPropagation()}
          >
            <input
              type="text"
              defaultValue={tag.name}
              ref={tagNameInputRef}
              autoFocus
              onKeyDown={handleInputKeyDown}
            />
            <ShowDeleteTagDialogButton tag={tag} onClick={closePopover} />
            <button type="submit" aria-label="제출" style={{ display: 'none' }}>
              제출
            </button>
          </form>
        </>
      )}
    </>
  );
}

interface TagInfoEditPopoverButtonProps {
  tag: tagTypes.TagType;
}
