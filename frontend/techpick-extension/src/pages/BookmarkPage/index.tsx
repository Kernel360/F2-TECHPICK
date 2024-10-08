// Todo: 추후 CurrentTabInfo활용 예정.
// import { CurrentTabInfo } from '@/components';
import { Button, Text } from '@/shared';
import { TagPicker } from '@/widgets';
import { BookmarkHeader } from './BookmarkHeader';
import { BookmarkPageLayout } from './BookmarkPage.css';

export function BookmarkPage() {
  return (
    <div className={BookmarkPageLayout}>
      <BookmarkHeader />
      <div style={{ paddingTop: '24px' }}></div>
      <div
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '32px',
        }}
      >
        <div style={{ display: 'flex', gap: '16px', alignItems: 'center' }}>
          <img src="" alt="" width={48} height={48} />
          <input
            type="text"
            defaultValue={'타이틀'}
            style={{ width: '100%', height: '40px', padding: '8px' }}
          />
        </div>
        <div style={{ display: 'flex', gap: '16px', color: 'A5B68D' }}>
          <div style={{ width: '48px' }}>
            <Text size="2xl">태그</Text>
          </div>

          <TagPicker />
        </div>
        <div style={{ display: 'flex', gap: '16px' }}>
          <div style={{ width: '48px' }}>
            <Text size="2xl">메모</Text>
          </div>
          <textarea
            name=""
            id="dad"
            style={{ width: '264px', height: '72px', resize: 'none' }}
          ></textarea>
        </div>
        <div style={{ marginLeft: 'auto' }}>
          <Button>제출</Button>
        </div>
      </div>
    </div>
  );
}
