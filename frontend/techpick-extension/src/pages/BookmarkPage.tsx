// Todo: 추후 CurrentTabInfo활용 예정.
// import { CurrentTabInfo } from '@/components';

import { TagPicker } from '@/widgets';

export function BookmarkPage() {
  return (
    <div style={{ width: '600px', height: '600px' }}>
      <h1>Bookmark page</h1>
      <TagPicker />
    </div>
  );
}
