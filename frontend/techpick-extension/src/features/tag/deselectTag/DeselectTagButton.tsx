import { X } from 'lucide-react';
import { tagTypes, useTagStore } from '@/entities/tag';
import { DeselectTagButtonStyle } from './DeselectTagButton.css';

export function DeselectTagButton({ tag }: { tag: tagTypes.TagType }) {
  const { deselectTag } = useTagStore();

  return (
    <button
      type="button"
      className={DeselectTagButtonStyle}
      onClick={() => {
        deselectTag(tag);
      }}
    >
      <X size={8} />
    </button>
  );
}
