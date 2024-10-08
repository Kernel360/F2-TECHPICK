// Todo: 추후 CurrentTabInfo활용 예정.
// import { CurrentTabInfo } from '@/components';
import { Button, Text, Gap } from '@/shared';
import { TagPicker } from '@/widgets';
import { BookmarkHeader } from './BookmarkHeader';
import {
  bookmarkPageLayout,
  pickFormLayout,
  formFieldLayout,
  titleInputStyle,
  textAreaStyle,
  submitButtonLayout,
  labelLayout,
} from './BookmarkPage.css';

export function BookmarkPage() {
  return (
    <div className={bookmarkPageLayout}>
      <BookmarkHeader />
      <Gap verticalSize="gap24" />
      <div className={pickFormLayout}>
        <div className={formFieldLayout}>
          <img src="" alt="" width={48} height={48} />
          <input
            type="text"
            defaultValue={'타이틀'}
            className={titleInputStyle}
          />
        </div>
        <div className={formFieldLayout}>
          <div className={labelLayout}>
            <Text size="2xl" asChild>
              <label htmlFor="">태그</label>
            </Text>
          </div>
          <TagPicker />
        </div>
        <div className={formFieldLayout}>
          <div className={labelLayout}>
            <Text size="2xl" asChild>
              <label htmlFor="">메모</label>
            </Text>
          </div>
          <textarea id="memo" className={textAreaStyle}></textarea>
        </div>
        <div className={submitButtonLayout}>
          <Button>제출</Button>
        </div>
      </div>
    </div>
  );
}
