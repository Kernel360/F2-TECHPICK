import { BookMarked } from 'lucide-react';
import { Text } from '@/shared';
import { BookmarkHeaderLayout } from './BookmarkHeader.css';

export function BookmarkHeader() {
  return (
    <div className={BookmarkHeaderLayout}>
      <BookMarked size={20} />
      <Text size="2xl" asChild>
        <h1>PICK</h1>
      </Text>
    </div>
  );
}
