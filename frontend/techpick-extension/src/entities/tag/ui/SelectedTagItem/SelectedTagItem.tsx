import { CSSProperties } from 'react';
import { numberToRandomColor } from '@/shared';
import { tagTypes } from '@/entities/tag';
import { SelectedTagContent } from './SelectedTagContent';
import { SelectedTagLayout } from './SelectedTagLayout';

export function SelectedTagItem({ tag, children }: SelectedTagItemProps) {
  const backgroundColor = numberToRandomColor(tag.colorNumber);
  const style: CSSProperties = { backgroundColor };

  return (
    <SelectedTagLayout style={style}>
      <SelectedTagContent>{tag.name}</SelectedTagContent>
      {children}
    </SelectedTagLayout>
  );
}

interface SelectedTagItemProps {
  tag: tagTypes.TagType;
  children?: React.ReactNode;
}
