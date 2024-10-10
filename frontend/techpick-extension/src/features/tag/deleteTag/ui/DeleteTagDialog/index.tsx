import { useRef } from 'react';
import * as Dialog from '@radix-ui/react-dialog';
import { Text, Button, Gap } from '@/shared';
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

    // todo: delete 시에 선택된 태그라면 같이 제거되야함. 추가적인 login 필요.
    deleteTag(deleteTagId);
    closeDialog();
  };

  return (
    <Dialog.Root open={isOpen} onOpenChange={setIsOpen}>
      <Dialog.Portal>
        <Dialog.Overlay className={dialogOverlayStyle} />
        <Dialog.Content className={dialogContentStyle}>
          <Text>이 태그를 삭제하시겠습니까?</Text>

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
