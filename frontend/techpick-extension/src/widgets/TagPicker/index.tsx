import { useRef, useState } from 'react';
import {
  useTagStore,
  SelectedTagItem,
  SelectedTagListLayout,
} from '@/entities/tag';
import { TagSelectionDialog } from '@/widgets/TagSelectionDialog';

import { tagInputStyle, tagDialogTrigger } from './TagPicker.css';

export function TagPicker() {
  const [open, setOpen] = useState(false);

  const tagInputContainerRef = useRef<HTMLDivElement>(null);

  const { selectedTagList } = useTagStore();

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
          {selectedTagList.map((tag) => (
            <SelectedTagItem key={tag.id} tag={tag} />
          ))}
        </SelectedTagListLayout>
      </div>

      <TagSelectionDialog
        open={open}
        onOpenChange={setOpen}
        container={tagInputContainerRef}
      />
    </div>
  );
}
