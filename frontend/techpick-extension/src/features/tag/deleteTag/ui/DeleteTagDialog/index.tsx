import { useRef } from 'react';
import * as Dialog from '@radix-ui/react-dialog';
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
          {/* 안의 내용과 디자인은 다음 PR에서 작업하겠습니다. */}
          <p>정말 삭제하시겠습니까?</p>

          <Dialog.Close asChild>
            <button ref={cancelButtonRef} onClick={closeDialog}>
              Cancel
            </button>
          </Dialog.Close>

          <button onClick={handleDeleteTag}>Yes, delete account</button>
        </Dialog.Content>
      </Dialog.Portal>
    </Dialog.Root>
  );
}
