import { Button } from '@/shared';
import type { tagTypes } from '@/entities/tag';
import { useDeleteTagDialogStore } from '../deleteTag.model';

export function ShowDeleteTagDialogButton({
  tag,
  onClick: parentOnClick = () => {},
}: ShowDeleteTagDialogButtonProps) {
  const { setIsOpen, setDeleteTagId } = useDeleteTagDialogStore();

  const showDeleteTagDialog = () => {
    setIsOpen(true);
    setDeleteTagId(tag.tagId);
    parentOnClick();
  };

  return (
    <Button onClick={showDeleteTagDialog} size="xs" background="warning" wide>
      삭제
    </Button>
  );
}

interface ShowDeleteTagDialogButtonProps {
  tag: tagTypes.TagType;
  onClick?: () => void;
}
