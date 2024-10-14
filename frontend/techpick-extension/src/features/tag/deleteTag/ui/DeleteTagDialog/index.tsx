import { useRef } from 'react';
import * as Dialog from '@radix-ui/react-dialog';
import * as VisuallyHidden from '@radix-ui/react-visually-hidden';
import { Text, Button, Gap, notifyError } from '@/shared';
import { useTagStore } from '@/entities/tag';
import { useDeleteTagDialogStore } from '../../deleteTag.model';
import { dialogContentStyle, dialogOverlayStyle } from './DeleteTagDialog.css';

export function DeleteTagDialog() {
  const cancelButtonRef = useRef<HTMLButtonElement | null>(null);
  const { deleteTag } = useTagStore();
  const { deleteTagId, isOpen, setIsOpen } = useDeleteTagDialogStore();

  const closeDialog = () => {
    setIsOpen(false);
  };

  const handleDeleteTag = (
    e: React.MouseEvent<HTMLButtonElement, MouseEvent>
  ) => {
    e.stopPropagation();

    if (!deleteTagId) {
      return;
    }

    try {
      deleteTag(deleteTagId);
      closeDialog();
    } catch (error) {
      if (error instanceof Error) {
        notifyError(error.message);
      }
    }
  };

  return (
    <Dialog.Root open={isOpen} onOpenChange={setIsOpen}>
      <Dialog.Portal>
        <Dialog.Overlay className={dialogOverlayStyle} />
        <Dialog.Content className={dialogContentStyle}>
          <Text>이 태그를 삭제하시겠습니까?</Text>

          <VisuallyHidden.Root>
            <Dialog.Title>이 태그를 삭제하시겠습니까?</Dialog.Title>
            <Dialog.Description>
              태그를 삭제하실 거라면 삭제 버튼을 눌러주세요.
            </Dialog.Description>
          </VisuallyHidden.Root>

          <div>
            <Button
              onClick={handleDeleteTag}
              size="xs"
              background="warning"
              wide
            >
              삭제
            </Button>
            <Gap verticalSize="gap4" />
            <Dialog.Close asChild>
              <Button
                ref={cancelButtonRef}
                onClick={closeDialog}
                size="xs"
                wide
              >
                취소
              </Button>
            </Dialog.Close>
          </div>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
}
