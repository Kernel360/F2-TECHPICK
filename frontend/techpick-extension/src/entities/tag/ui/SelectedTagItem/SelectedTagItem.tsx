import { tagTypes } from '@/entities/tag';
import { SelectedTagContent } from './SelectedTagContent';
import { SelectedTagLayout } from './SelectedTagLayout';

export function SelectedTagItem({ tag, children }: SelectedTagItemProps) {
  return (
    <SelectedTagLayout>
      <SelectedTagContent>{tag.name}</SelectedTagContent>
      {children}
    </SelectedTagLayout>
  );
}

interface SelectedTagItemProps {
  tag: tagTypes.TagType;
  children?: React.ReactNode;
}
