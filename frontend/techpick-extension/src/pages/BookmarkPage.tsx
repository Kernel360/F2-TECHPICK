// TODO
// 1. select, deselect 로직 구현 - 완료
// 2. get logic 구현. - local storage를 이용한 cache도 고려. - 추후 고려
// 3. create 로직 구현.
// 4. delete 로직 구현.
// 5. update 로직 구현.

// import { CurrentTabInfo } from '@/components';
import { TagPicker } from '@/widgets';

export function BookmarkPage() {
  return (
    <div style={{ width: '600px', height: '600px' }}>
      <h1>Bookmark page</h1>

      <TagPicker />
      {/* <CurrentTabInfo /> */}
    </div>
  );
}
