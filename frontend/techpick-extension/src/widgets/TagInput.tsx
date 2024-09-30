import { useEffect, useRef, useState } from 'react';
import { Command } from 'cmdk';
import {
  SelectedTagContent,
  SelectedTagLayout,
  useTagStore,
  SelectedTagListLayout,
} from '@/entities/tag';
import { DeselectTagButton } from '@/features/tag';
import {
  tagInputStyle,
  tagDialogTrigger,
  tagDialogPortal,
  selectedTagItemStyle,
} from './TagInput.css';

export function TagInput() {
  const [open, setOpen] = useState(false);
  const tagInputContainerRef = useRef<HTMLDivElement | null>(null);
  const {
    tagList,
    selectedTagList,
    fetchingTagState,
    selectTag,
    fetchingTagList,
  } = useTagStore();

  const openDialog = () => {
    setOpen(true);
  };

  useEffect(() => {
    fetchingTagList();
  }, [fetchingTagList]);

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
        label="Global Command Menu"
        container={tagInputContainerRef.current ?? undefined}
        className={tagDialogPortal}
      >
        <div>
          <SelectedTagListLayout>
            {selectedTagList.map((tag) => (
              <SelectedTagLayout key={tag.id}>
                <SelectedTagContent>{tag.name}</SelectedTagContent>
                <DeselectTagButton tag={tag} />
              </SelectedTagLayout>
            ))}
          </SelectedTagListLayout>
        </div>

        <Command.List>
          <Command.Empty>No results found.</Command.Empty>

          {tagList.map((data) => (
            <Command.Item
              key={data.id}
              className={selectedTagItemStyle}
              onSelect={() => selectTag(data)}
            >
              {data.name}
            </Command.Item>
          ))}
        </Command.List>
      </Command.Dialog>
    </div>
  );
}
