import DOMPurify from 'dompurify';
import {
  Button,
  Text,
  useChangeFocusUsingArrowKey,
  notifySuccess,
  notifyError,
  returnErrorFromHTTPError,
} from '@/shared';
import { tagTypes, useTagStore } from '@/entities/tag';
import { updatePick } from '@/entities/pick';
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
import { useEffect, useRef } from 'react';
import { HTTPError } from 'ky';

export function UpdatePickForm({
  id,
  title,
  tagList,
  memo,
  imageUrl,
}: UpdatePickFormProps) {
  const titleInputRef = useRef<HTMLInputElement>(null);
  const tagPickerRef = useRef<HTMLDivElement>(null);
  const memoInputRef = useRef<HTMLTextAreaElement>(null);
  const submitButtonRef = useRef<HTMLButtonElement>(null);
  const { selectedTagList, replaceSelectedTagList } = useTagStore();
  useChangeFocusUsingArrowKey([
    titleInputRef,
    tagPickerRef,
    memoInputRef,
    submitButtonRef,
  ]);

  useEffect(
    function onLoad() {
      replaceSelectedTagList(tagList);
    },
    [tagList, replaceSelectedTagList]
  );

  const onSubmit = () => {
    const userModifiedTitle = titleInputRef.current?.value ?? '';
    const userMemo = memoInputRef.current?.value ?? '';

    updatePick({
      id,
      title: DOMPurify.sanitize(userModifiedTitle),
      memo: DOMPurify.sanitize(userMemo),
      tagIdList: selectedTagList.map((tag) => tag.tagId),
    })
      .then(() => {
        notifySuccess('수정되었습니다!');
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
          defaultValue={memo}
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

interface UpdatePickFormProps {
  id: number;
  title: string;
  tagList: tagTypes.TagType[];
  imageUrl: string;
  memo: string;
}
