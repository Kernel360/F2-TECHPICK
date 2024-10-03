import { useEffect, useRef, useState } from 'react';
import { Command } from 'cmdk';
import {
  SelectedTagItem,
  SelectedTagListLayout,
  useTagStore,
} from '@/entities/tag';
import { DeleteTagDialog, DeselectTagButton } from '@/features/tag';
import { TagInfoEditPopoverButton } from '@/widgets/TagInfoEditPopoverButton';

import { filterCommandItems } from './TagAutocompleteDialog.lib';
import {
  tagDialogPortalLayout,
  commandInputLayout,
  commandInputStyle,
  selectedTagListItemStyle,
} from './TagAutocompleteDialog.css';

export function TagAutocompleteDialog({
  open,
  onOpenChange,
  container,
}: TagSelectionDialogProps) {
  const [tagInputValue, setTagInputValue] = useState('');
  const [canCreateTag, setCanCreateTag] = useState(false);
  const tagInputRef = useRef<HTMLInputElement | null>(null);
  const {
    tagList,
    selectedTagList,
    fetchingTagState,
    selectTag,
    fetchingTagList,
    createTag,
  } = useTagStore();

  const focusTagInput = () => {
    tagInputRef.current?.focus();
  };

  const clearTagInputValue = () => {
    setTagInputValue('');
  };

  useEffect(
    function fetchTagList() {
      fetchingTagList();
    },
    [fetchingTagList]
  );

  useEffect(
    function checkIsCreatableTag() {
      const isUnique = !tagList.some((tag) => tag.name === tagInputValue);
      const isNotInitialValue = tagInputValue.trim() !== '';
      const isCreatable = isUnique && isNotInitialValue;

      setCanCreateTag(isCreatable);
    },
    [tagInputValue, tagList]
  );

  if (fetchingTagState.isPending) {
    return <h1>Loading...</h1>;
  }

  return (
    <Command.Dialog
      open={open}
      onOpenChange={onOpenChange}
      container={container?.current ?? undefined}
      className={tagDialogPortalLayout}
      filter={filterCommandItems}
    >
      {/**선택한 태그 리스트 */}
      <SelectedTagListLayout>
        {selectedTagList.map((tag) => (
          <SelectedTagItem key={tag.id} tag={tag}>
            <DeselectTagButton tag={tag} onClick={focusTagInput} />
          </SelectedTagItem>
        ))}

        <div className={commandInputLayout}>
          <Command.Input
            className={commandInputStyle}
            ref={tagInputRef}
            value={tagInputValue}
            onValueChange={setTagInputValue}
          />
        </div>
      </SelectedTagListLayout>

      {/**전체 태그 리스트 */}
      <Command.List>
        <Command.Empty>No results found.</Command.Empty>

        {tagList.map((tag) => (
          <Command.Item
            key={tag.id}
            className={selectedTagListItemStyle}
            onSelect={() => {
              selectTag(tag);
              focusTagInput();
              clearTagInputValue();
            }}
            keywords={[tag.name]}
          >
            {tag.name}
            <TagInfoEditPopoverButton tag={tag} />
          </Command.Item>
        ))}

        {canCreateTag && (
          <Command.Item
            className={selectedTagListItemStyle}
            value={tagInputValue}
            keywords={['생성']}
            onSelect={async () => {
              const newTag = await createTag(tagInputValue);

              if (newTag) {
                selectTag(newTag);
                focusTagInput();
                clearTagInputValue();
              }
            }}
            disabled={!canCreateTag}
          >
            {tagInputValue} 생성
          </Command.Item>
        )}
      </Command.List>

      <DeleteTagDialog />
    </Command.Dialog>
  );
}

interface TagSelectionDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  container?: React.RefObject<HTMLDivElement>;
}
