import { Button, Text } from '@/shared';
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

export function SkeltonPickForm({ title }: SkeltonPickFormProps) {
  return (
    <form className={pickFormLayout} onSubmit={(e) => e.preventDefault()}>
      <div className={formFieldLayout}>
        <ThumbnailImage image={null} />
        <input type="text" defaultValue={title} className={titleInputStyle} />
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
    </form>
  );
}

interface SkeltonPickFormProps {
  title: string;
}
