import { useEffect, useRef, useState } from 'react';
import { Command } from 'cmdk';
import { BarLoader } from 'react-spinners';
import { numberToRandomColor } from '@/shared';
import {
  SelectedTagItem,
  SelectedTagListLayout,
  useTagStore,
  tagTypes,
} from '@/entities/tag';
import { DeleteTagDialog, DeselectTagButton } from '@/features/tag';
import { TagInfoEditPopoverButton } from '@/widgets/TagPicker/TagInfoEditPopoverButton';
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
}: TagSelectionDialogProps) {
  const [tagInputValue, setTagInputValue] = useState('');
  const [canCreateTag, setCanCreateTag] = useState(false);
  const [commandListHeight, setCommandListHeight] = useState(0);
  const tagInputRef = useRef<HTMLInputElement | null>(null);
  const selectedTagListRef = useRef<HTMLDivElement | null>(null);
  const randomNumber = useRef<number>(getRandomInt());

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

  const onSelectTag = (tag: tagTypes.TagType) => {
    selectTag(tag);
    focusTagInput();
    clearTagInputValue();
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

  useEffect(
    function calculateCommandListHeight() {
      const COMMAND_LIST_INITIAL_HEIGHT = 160;
      const COMMAND_LIST_MAX_HEIGHT = 208;

      if (!selectedTagListRef.current) {
        // 초기에는 초기값 부여
        setCommandListHeight(COMMAND_LIST_INITIAL_HEIGHT);
        return;
      }

      const { height } = selectedTagListRef.current.getBoundingClientRect();
      const commandListHeight = Math.max(
        0,
        Math.min(COMMAND_LIST_INITIAL_HEIGHT, COMMAND_LIST_MAX_HEIGHT - height)
      );
      setCommandListHeight(commandListHeight);
    },
    [selectedTagList]
  );

  return (
    <Command.Dialog
      open={open}
      onOpenChange={onOpenChange}
      container={container?.current ?? undefined}
      className={tagDialogPortalLayout}
      filter={filterCommandItems}
    >
      {/**선택한 태그 리스트 */}
      <SelectedTagListLayout
        ref={selectedTagListRef} // ref 추가
        focusStyle="focus"
      >
        {selectedTagList.map((tag) => (
          <SelectedTagItem key={tag.id} tag={tag}>
            <DeselectTagButton tag={tag} onClick={focusTagInput} />
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
            <BarLoader />
          </Command.Loading>
        )}

        {!fetchingTagState.isPending && (
          <Command.Empty className={tagListItemStyle}>
            태그를 만들어보세요!
          </Command.Empty>
        )}

        {tagList.map((tag) => (
          <Command.Item
            key={tag.id}
            className={tagListItemStyle}
            onSelect={() => onSelectTag(tag)}
            keywords={[tag.name]}
          >
            <span
              className={tagListItemContentStyle}
              style={{
                backgroundColor: numberToRandomColor(tag.colorNumber),
              }}
            >
              {tag.name}
            </span>
            <TagInfoEditPopoverButton tag={tag} />
          </Command.Item>
        ))}

        {canCreateTag && (
          <Command.Item
            className={tagListItemStyle}
            value={tagInputValue}
            keywords={[CREATABLE_TAG_KEYWORD]}
            onSelect={async () => {
              const newTag = await createTag(
                tagInputValue,
                randomNumber.current
              );

              randomNumber.current = getRandomInt();

              if (!newTag) {
                // Todo: error handling
                return;
              }

              onSelectTag(newTag);
            }}
            disabled={!canCreateTag}
          >
            <span
              className={tagListItemContentStyle}
              style={{
                backgroundColor: numberToRandomColor(randomNumber.current),
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
}
