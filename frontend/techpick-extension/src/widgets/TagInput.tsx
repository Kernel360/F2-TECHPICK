// TODO
// 1. select 부드럽게
// 2. 특정 tag 선택시에는 두 화면에서 다 보여야함.
// 3. 생성, 삭제 시에도 동일.

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
    // TODO: 1. 현재 펼쳐질 때마다, 계속 불러옴.
    // TODO: 2. 로컬 스토리지 사용하기. => zustand와 연동하고, 저장 시간 확인하기.
    // TODO: 3. 캐싱.

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
