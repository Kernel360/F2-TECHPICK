import DOMPurify from 'dompurify';
import {
  Button,
  Text,
  useChangeFocusUsingArrowKey,
  notifySuccess,
  notifyError,
  returnErrorFromHTTPError,
} from '@/shared';
import { useTagStore } from '@/entities/tag';
import { createPick } from '@/entities/pick';
import { TagPicker } from '@/widgets';
import { ThumbnailImage } from './ThumbnailImage';
import {
  pickFormLayout,
  formFieldLayout,
  titleInputStyle,
  textAreaStyle,
  submitButtonLayout,
  labelLayout,
} from './BookmarkPage.css';
import { useRef } from 'react';
import { HTTPError } from 'ky';

export function CreatePickForm({
  title,
  url,
  imageUrl,
  description,
}: CreatePickFormProps) {
  const titleInputRef = useRef<HTMLInputElement>(null);
  const tagPickerRef = useRef<HTMLDivElement>(null);
  const memoInputRef = useRef<HTMLTextAreaElement>(null);
  const submitButtonRef = useRef<HTMLButtonElement>(null);
  const { selectedTagList } = useTagStore();
  useChangeFocusUsingArrowKey([
    titleInputRef,
    tagPickerRef,
    memoInputRef,
    submitButtonRef,
  ]);

  const onSubmit = () => {
    const userModifiedTitle = titleInputRef.current?.value ?? '';
    const userMemo = memoInputRef.current?.value ?? '';

    createPick({
      title: DOMPurify.sanitize(userModifiedTitle),
      memo: DOMPurify.sanitize(userMemo),
      tagIdList: selectedTagList.map((tag) => tag.tagId),
      linkRequest: {
        title,
        url,
        imageUrl,
        description,
      },
    })
      .then(() => {
        notifySuccess('저장되었습니다!');
      })
      .catch(async (httpError: HTTPError) => {
        const error = await returnErrorFromHTTPError(httpError);
        notifyError(`${error.message}로 인해 북마크가 실패했습니다!`);
      });
  };

  return (
    <form className={pickFormLayout} onSubmit={(e) => e.preventDefault()}>
      <div className={formFieldLayout}>
        <ThumbnailImage image={imageUrl} />
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
  );
}

interface CreatePickFormProps {
  imageUrl: string;
  title: string;
  url: string;
  description: string;
}
