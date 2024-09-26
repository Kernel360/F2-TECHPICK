// TODO
// 1. select 부드럽게
// 2. 특정 tag 선택시에는 두 화면에서 다 보여야함.
// 3. 생성, 삭제 시에도 동일.

import { useRef, useState } from 'react';
import { Command } from 'cmdk';
import {
  SelectedTag,
  useSelectTagStore,
  SelectedTagListLayout,
} from '@/entities/tag';
import {
  tagInputStyle,
  tagDialogTrigger,
  tagDialogPortal,
  selectedTagItemStyle,
} from './TagInput.css';

const SAMPLE_DATA = [
  { id: 1, value: 'react' },
  { id: 2, value: 'typescript' },
  { id: 3, value: 'ci/cd' },
  { id: 4, value: 'react-custom-timetable' },
  { id: 5, value: 'dasdsaasda' },
  { id: 5, value: 'dasdsaasda-dasdsaasda-dasdsaasda' },
];

export function TagInput() {
  const [open, setOpen] = useState(false);
  const tagInputContainerRef = useRef<HTMLDivElement | null>(null);
  const { tagList, selectTag } = useSelectTagStore();

  const openDialog = () => {
    setOpen(true);
  };

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
          {tagList.map((tag) => (
            <SelectedTag key={tag.id}>{tag.value}</SelectedTag>
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
            {tagList.map((tag) => (
              <SelectedTag key={tag.id}>{tag.value}</SelectedTag>
            ))}
          </SelectedTagListLayout>
        </div>

        <Command.List>
          <Command.Empty>No results found.</Command.Empty>

          {SAMPLE_DATA.map((data) => (
            <Command.Item
              key={data.id}
              className={selectedTagItemStyle}
              onSelect={() => selectTag(data)}
            >
              {data.value}
            </Command.Item>
          ))}
        </Command.List>
      </Command.Dialog>
    </div>
  );
}
