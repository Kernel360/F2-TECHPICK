import { CSSProperties } from 'react';
import { numberToRandomColor } from '@/utils';
import { useThemeStore } from '@/stores/themeStore';
import { TagType } from '@/types';
import { SelectedTagContent } from './SelectedTagContent';
import { SelectedTagLayout } from './SelectedTagLayout';

export function SelectedTagItem({ tag, children }: SelectedTagItemProps) {
  const { isDarkMode } = useThemeStore();
  const backgroundColor = numberToRandomColor(
    tag.colorNumber,
    isDarkMode ? 'dark' : 'light'
  );
  const style: CSSProperties = { backgroundColor };

  return (
    <SelectedTagLayout style={style}>
      <SelectedTagContent>{tag.tagName}</SelectedTagContent>
      {children}
    </SelectedTagLayout>
  );
}

interface SelectedTagItemProps {
  tag: TagType;
  children?: React.ReactNode;
}
