'use client';

import { forwardRef, useEffect, useRef, useState } from 'react';
import {
  SelectedTagItem,
  SelectedTagListLayout,
  tagTypes,
} from '@/entities/tag';
import { useGetPickQuery } from '@/entities/pick/api';
import { TagAutocompleteDialog } from './TagAutocompleteDialog';
import { tagPickerLayout, tagDialogTriggerLayout } from './TagPicker.css';

export const TagPicker = forwardRef<HTMLDivElement, TagPickerProps>(
  function TagPickerWithRef({ pickId }, tabFocusRef) {
    const [open, setOpen] = useState(false);
    const [selectedTagList, setSelectedTagList] = useState<tagTypes.TagType[]>(
      []
    );
    const tagInputContainerRef = useRef<HTMLDivElement>(null);
    const { data: pickData } = useGetPickQuery(pickId);

    useEffect(
      function tagPickerLoad() {
        if (!pickData) {
          return;
        }

        setSelectedTagList(pickData.tagList);
      },
      [pickData]
    );

    const openDialog = () => {
      setOpen(true);
    };

    const onEnterKeyDown = (e: React.KeyboardEvent<HTMLDivElement>) => {
      if (e.key !== 'Enter') {
        return;
      }

      openDialog();
    };

    return (
      <div ref={tagInputContainerRef} className={tagPickerLayout}>
        <div
          className={tagDialogTriggerLayout}
          onClick={(e) => {
            console.log('tagDialogTriggerLayout click');
            e.preventDefault();
            openDialog();
          }}
          onKeyDown={onEnterKeyDown}
          tabIndex={0}
          ref={tabFocusRef}
        >
          <SelectedTagListLayout height="fixed">
            {selectedTagList.map((tag) => (
              <SelectedTagItem key={tag.tagName} tag={tag} />
            ))}
          </SelectedTagListLayout>
        </div>

        <TagAutocompleteDialog
          open={open}
          onOpenChange={setOpen}
          container={tagInputContainerRef}
          pickId={pickId}
          selectedTagList={selectedTagList}
          setSelectedTagList={setSelectedTagList}
        />
      </div>
    );
  }
);

interface TagPickerProps {
  pickId: number;
}
