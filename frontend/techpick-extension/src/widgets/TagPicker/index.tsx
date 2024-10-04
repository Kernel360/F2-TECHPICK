import { useRef, useState } from 'react';
import {
  useTagStore,
  SelectedTagItem,
  SelectedTagListLayout,
} from '@/entities/tag';
import { TagAutocompleteDialog } from './TagAutocompleteDialog';
import { tagPickerLayout, tagDialogTriggerLayout } from './TagPicker.css';

export function TagPicker() {
  const [open, setOpen] = useState(false);

  const tagInputContainerRef = useRef<HTMLDivElement>(null);

  const { selectedTagList } = useTagStore();

  const openDialog = () => {
    setOpen(true);
  };

  return (
    <div ref={tagInputContainerRef} className={tagPickerLayout}>
      <div className={tagDialogTriggerLayout} onClick={openDialog}>
        <SelectedTagListLayout>
          {selectedTagList.map((tag) => (
            <SelectedTagItem key={tag.id} tag={tag} />
          ))}
        </SelectedTagListLayout>
      </div>

      <TagAutocompleteDialog
        open={open}
        onOpenChange={setOpen}
        container={tagInputContainerRef}
      />
    </div>
  );
}
