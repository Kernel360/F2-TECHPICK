import { useGetTabInfo } from '@/hooks';
import { Button, Text, Gap, useChangeFocusUsingArrowKey } from '@/shared';
import { useTagStore } from '@/entities/tag';
import { TagPicker } from '@/widgets';
import { BookmarkHeader } from './BookmarkHeader';
import { ThumbnailImage } from './ThumbnailImage';
import {
  bookmarkPageLayout,
  pickFormLayout,
  formFieldLayout,
  titleInputStyle,
  textAreaStyle,
  submitButtonLayout,
  labelLayout,
} from './BookmarkPage.css';
import { useRef } from 'react';

export function BookmarkPage() {
  const titleInputRef = useRef<HTMLInputElement>(null);
  const tagPickerRef = useRef<HTMLDivElement>(null);
  const memoInputRef = useRef<HTMLTextAreaElement>(null);
  const submitButtonRef = useRef<HTMLButtonElement>(null);
  const { selectedTagList } = useTagStore();
  const { ogImage, title } = useGetTabInfo();
  useChangeFocusUsingArrowKey([
    titleInputRef,
    tagPickerRef,
    memoInputRef,
    submitButtonRef,
  ]);

  const onSubmit = () => {
    // todo: api 연결하기
    console.log('titleInputRef', titleInputRef.current?.value);
    console.log('memoInputRef', memoInputRef.current?.value);
    console.log('selectedTagList', selectedTagList);
  };

  return (
    <div className={bookmarkPageLayout}>
      <BookmarkHeader />
      <Gap verticalSize="gap24" />
      <form className={pickFormLayout} onSubmit={(e) => e.preventDefault()}>
        <div className={formFieldLayout}>
          <ThumbnailImage image={ogImage} />
          <input
            type="text"
            defaultValue={title}
            ref={titleInputRef}
            className={titleInputStyle}
          />
        </div>
        <div className={formFieldLayout}>
          <div className={labelLayout}>
            <Text size="2xl" asChild>
              <label htmlFor="">태그</label>
            </Text>
          </div>
          <TagPicker ref={tagPickerRef} />
        </div>
        <div className={formFieldLayout}>
          <div className={labelLayout}>
            <Text size="2xl" asChild>
              <label htmlFor="">메모</label>
            </Text>
          </div>
          <textarea
            id="memo"
            className={textAreaStyle}
            ref={memoInputRef}
          ></textarea>
        </div>
        <div className={submitButtonLayout}>
          <Button onClick={onSubmit} ref={submitButtonRef}>
            제출
          </Button>
        </div>
      </form>
    </div>
  );
}
