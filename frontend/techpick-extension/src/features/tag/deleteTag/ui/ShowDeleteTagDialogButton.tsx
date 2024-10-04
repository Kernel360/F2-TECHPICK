import type { tagTypes } from '@/entities/tag';
import { useDeleteTagDialogStore } from '../deleteTag.model';

export function ShowDeleteTagDialogButton({
  tag,
  onClick: parentOnClick = () => {},
}: ShowDeleteTagDialogButtonProps) {
  const { setIsOpen, setDeleteTagId } = useDeleteTagDialogStore();

  const showDeleteTagDialog = () => {
    setIsOpen(true);
    setDeleteTagId(tag.id);
    parentOnClick();
  };

  return (
    <button type="button" onClick={showDeleteTagDialog}>
      삭제
    </button>
  );
}

interface ShowDeleteTagDialogButtonProps {
  tag: tagTypes.TagType;
  onClick?: () => void;
}
