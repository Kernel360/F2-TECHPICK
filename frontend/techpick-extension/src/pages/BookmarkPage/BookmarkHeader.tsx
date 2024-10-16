import { BookMarked } from 'lucide-react';
import { colorThemeContract } from 'techpick-shared';
import { Text } from '@/shared';
import { ToggleThemeButton } from '@/features/changeTheme';
import { BookmarkHeaderLayout, logoSectionLayout } from './BookmarkHeader.css';

export function BookmarkHeader() {
  return (
    <div className={BookmarkHeaderLayout}>
      <div className={logoSectionLayout}>
        <BookMarked size={20} color={colorThemeContract.color.font} />
        <Text size="2xl" asChild>
          <h1>PICK</h1>
        </Text>
      </div>
      <ToggleThemeButton />
    </div>
  );
}
