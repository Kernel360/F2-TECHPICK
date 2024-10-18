import { X } from 'lucide-react';
import { tagTypes, useTagStore } from '@/entities/tag';
import { DeselectTagButtonStyle } from './DeselectTagButton.css';

export function DeselectTagButton({
  tag,
  onClick = () => {},
}: DeselectTagButtonProps) {
  const { deselectTag } = useTagStore();

  return (
    <button
      type="button"
      className={DeselectTagButtonStyle}
      onClick={() => {
        deselectTag(tag.tagId);
        onClick();
      }}
    >
      <X size={8} />
    </button>
  );
}

interface DeselectTagButtonProps {
  tag: tagTypes.TagType;
  onClick?: () => void;
}
