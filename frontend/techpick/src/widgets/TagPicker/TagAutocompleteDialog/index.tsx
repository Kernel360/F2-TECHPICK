'use client';

import { Dispatch, useEffect, useRef, useState } from 'react';
import { Command } from 'cmdk';
import { BarLoader } from 'react-spinners';
import { color } from 'techpick-shared';
import { useThemeStore } from '@/shared/stores/themeStore';
import { notifyError, numberToRandomColor } from '@/shared/lib';
import { useUpdatePickMutation, useGetPickQuery } from '@/entities/pick';
import {
  SelectedTagItem,
  SelectedTagListLayout,
  useTagStore,
  tagTypes,
} from '@/entities/tag';
import { DeleteTagDialog, DeselectTagButton } from '@/features/tag';
import { TagInfoEditPopoverButton } from '@/widgets/TagPicker/TagInfoEditPopoverButton';
import { useCalculateCommandListHeight } from './useCalculateCommandListHeight';
import {
  filterCommandItems,
  CREATABLE_TAG_KEYWORD,
  getRandomInt,
} from './TagAutocompleteDialog.lib';
import {
  tagDialogPortalLayout,
  commandInputStyle,
  tagListItemStyle,
  tagListItemContentStyle,
  tagCreateTextStyle,
  tagListStyle,
  tagListLoadingStyle,
} from './TagAutocompleteDialog.css';

export function TagAutocompleteDialog({
  open,
  onOpenChange,
  container,
  pickId,
  selectedTagList,
  setSelectedTagList,
}: TagSelectionDialogProps) {
  const [tagInputValue, setTagInputValue] = useState('');
  const [canCreateTag, setCanCreateTag] = useState(false);
  const tagInputRef = useRef<HTMLInputElement | null>(null);
  const selectedTagListRef = useRef<HTMLDivElement | null>(null);
  const randomNumber = useRef<number>(getRandomInt());
  const { tagList, fetchingTagState, fetchingTagList, createTag } =
    useTagStore();
  const { commandListHeight } =
    useCalculateCommandListHeight(selectedTagListRef);
  const { isDarkMode } = useThemeStore();
  const { data: pickData } = useGetPickQuery(pickId);
  const { mutate: updatePickInfo } = useUpdatePickMutation(pickId);

  const focusTagInput = () => {
    tagInputRef.current?.focus();
  };

  const clearTagInputValue = () => {
    setTagInputValue('');
  };

  const selectTag = (tag: tagTypes.TagType) => {
    const index = selectedTagList.findIndex(
      (selectedTag) => selectedTag.tagId === tag.tagId
    );

    if (index !== -1) {
      return;
    }

    setSelectedTagList([...selectedTagList, tag]);
  };

  const deselectTag = (tag: tagTypes.TagType) => {
    const filteredSelectedTagList = selectedTagList.filter(
      (selectedTag) => selectedTag.tagId !== tag.tagId
    );

    setSelectedTagList([...filteredSelectedTagList]);
  };

  const onSelectTag = (tag: tagTypes.TagType) => {
    selectTag(tag);
    focusTagInput();
    clearTagInputValue();
  };

  const onSelectCreatableTag = async () => {
    try {
      const newTag = await createTag({
        tagName: tagInputValue,
        colorNumber: randomNumber.current,
      });
      randomNumber.current = getRandomInt();
      onSelectTag(newTag!);

      if (!pickData || !newTag) {
        return;
      }

      const { title, memo, id } = pickData;

      const previousTagIdList = selectedTagList.map(
        (selectedTag) => selectedTag.tagId
      );

      updatePickInfo({
        title,
        memo,
        id,
        tagIdList: [...previousTagIdList, newTag.tagId],
      });
    } catch (error) {
      if (error instanceof Error) {
        notifyError(error.message);
      }
    }
  };

  useEffect(
    function fetchTagList() {
      fetchingTagList();
    },
    [fetchingTagList]
  );

  useEffect(
    function checkIsCreatableTag() {
      const isUnique = !tagList.some((tag) => tag.tagName === tagInputValue);
      const isNotInitialValue = tagInputValue.trim() !== '';
      const isCreatable = isUnique && isNotInitialValue;

      setCanCreateTag(isCreatable);
    },
    [tagInputValue, tagList]
  );

  return (
    <Command.Dialog
      open={open}
      onClick={(e) => {
        console.log('Command.Dialog click');
        e.stopPropagation();
        e.preventDefault();
      }}
      onOpenChange={async (open) => {
        onOpenChange(open);

        if (!open && pickData) {
          const { title, memo, id } = pickData;

          updatePickInfo({
            title,
            memo,
            id,
            tagIdList: selectedTagList.map((selectedTag) => selectedTag.tagId),
          });
        }

        // updatePickInfo()
        // 비동기 api 요청을 보내자!
      }}
      container={container?.current ?? undefined}
      className={tagDialogPortalLayout}
      filter={filterCommandItems}
    >
      {/**선택한 태그 리스트 */}
      <SelectedTagListLayout ref={selectedTagListRef} focusStyle="focus">
        {selectedTagList.map((tag) => (
          <SelectedTagItem key={tag.tagId} tag={tag}>
            <DeselectTagButton
              onClick={() => {
                focusTagInput();
                deselectTag(tag);
              }}
            />
          </SelectedTagItem>
        ))}

        <Command.Input
          className={commandInputStyle}
          ref={tagInputRef}
          value={tagInputValue}
          onValueChange={setTagInputValue}
        />
      </SelectedTagListLayout>

      {/**전체 태그 리스트 */}
      <Command.List
        className={tagListStyle}
        style={{ maxHeight: commandListHeight }}
      >
        {fetchingTagState.isPending && (
          <Command.Loading className={tagListLoadingStyle}>
            <BarLoader color={color.font} />
          </Command.Loading>
        )}

        {(!fetchingTagState.isPending || tagInputValue.trim()) !== '' && (
          <Command.Empty className={tagListItemStyle}>
            태그를 만들어보세요!
          </Command.Empty>
        )}

        {tagList.map((tag) => (
          <Command.Item
            key={tag.tagId}
            className={tagListItemStyle}
            onSelect={() => onSelectTag(tag)}
            keywords={[tag.tagName]}
          >
            <SelectedTagItem key={tag.tagId} tag={tag} />
            <TagInfoEditPopoverButton
              tag={tag}
              selectedTagList={selectedTagList}
              setSelectedTagList={setSelectedTagList}
            />
          </Command.Item>
        ))}

        {canCreateTag && (
          <Command.Item
            className={tagListItemStyle}
            value={tagInputValue}
            keywords={[CREATABLE_TAG_KEYWORD]}
            onSelect={onSelectCreatableTag}
            disabled={!canCreateTag}
          >
            <span
              className={tagListItemContentStyle}
              style={{
                backgroundColor: numberToRandomColor(
                  randomNumber.current,
                  isDarkMode ? 'dark' : 'light'
                ),
              }}
            >
              {tagInputValue}
            </span>
            <span className={tagCreateTextStyle}>생성</span>
          </Command.Item>
        )}
      </Command.List>

      {/**DeleteTagDialog를 닫고도 Command.Dialog가 켜져있기위해서 Command.Dialog 내부에 있어야합니다.*/}
      <DeleteTagDialog />
    </Command.Dialog>
  );
}

interface TagSelectionDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  container?: React.RefObject<HTMLElement>;
  pickId: number;
  selectedTagList: tagTypes.TagType[];
  setSelectedTagList: Dispatch<React.SetStateAction<tagTypes.TagType[]>>;
}
