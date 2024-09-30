import { useEffect, useRef, useState } from 'react';
import { Command } from 'cmdk';
import {
  SelectedTagContent,
  SelectedTagLayout,
  useTagStore,
  SelectedTagListLayout,
} from '@/entities/tag';
import { DeselectTagButton } from '@/features/tag';
import { TagInfoEditPopoverButton } from '@/widgets/TagInfoEditPopoverButton';
import {
  tagInputStyle,
  tagDialogTrigger,
  tagDialogPortal,
  selectedTagItemStyle,
  commandInputStyle,
  commandInputLayout,
} from './TagInput.css';
import { filterCommandItems } from './TagInput.lib';

export function TagInput() {
  const [open, setOpen] = useState(false);
  const [tagInputValue, setTagInputValue] = useState('');
  const [canCreateTag, setCanCreateTag] = useState(false);
  const tagInputContainerRef = useRef<HTMLDivElement | null>(null);
  const tagInputRef = useRef<HTMLInputElement | null>(null);
  const {
    tagList,
    selectedTagList,
    fetchingTagState,
    selectTag,
    fetchingTagList,
    createTag,
  } = useTagStore();

  const openDialog = () => {
    setOpen(true);
  };

  useEffect(() => {
    fetchingTagList();
  }, [fetchingTagList]);

  useEffect(() => {
    const isUnique = !tagList.some((tag) => tag.name === tagInputValue);
    const isNotInitialValue = tagInputValue.trim() !== '';
    const isCreatable = isUnique && isNotInitialValue;

    setCanCreateTag(isCreatable);
  }, [tagInputValue, tagList]);

  const focusTagInput = () => {
    tagInputRef.current?.focus();
  };

  const clearTagInputValue = () => {
    setTagInputValue('');
  };

  if (fetchingTagState.isPending) {
    return <h1>Loading...</h1>;
  }

  return (
    <div
      data-description="모달이 여기서 뜨게."
      id="tagInputContainer"
      ref={tagInputContainerRef}
      className={tagInputStyle}
    >
      <div
        className={tagDialogTrigger}
        data-description="여기를 선택하면 다이얼로그가 켜짐"
        onClick={openDialog}
      >
        <SelectedTagListLayout>
          {selectedTagList.map((tag) => (
            <SelectedTagLayout key={tag.id}>
              <SelectedTagContent>{tag.name}</SelectedTagContent>
            </SelectedTagLayout>
          ))}
        </SelectedTagListLayout>
      </div>
      <Command.Dialog
        open={open}
        onOpenChange={setOpen}
        container={tagInputContainerRef.current ?? undefined}
        className={tagDialogPortal}
        filter={filterCommandItems}
      >
        {/**선택한 태그 리스트 */}
        <SelectedTagListLayout>
          {selectedTagList.map((tag) => (
            <SelectedTagLayout key={tag.id}>
              <SelectedTagContent>{tag.name}</SelectedTagContent>
              <DeselectTagButton tag={tag} onClick={focusTagInput} />
            </SelectedTagLayout>
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

          {tagList.map((data) => (
            <Command.Item
              key={data.id}
              className={selectedTagItemStyle}
              onSelect={() => {
                selectTag(data);
                focusTagInput();
                clearTagInputValue();
              }}
              keywords={[data.name]}
            >
              {data.name}
              <TagInfoEditPopoverButton />
            </Command.Item>
          ))}

          {canCreateTag && (
            <Command.Item
              className={selectedTagItemStyle}
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
      </Command.Dialog>
    </div>
  );
}
