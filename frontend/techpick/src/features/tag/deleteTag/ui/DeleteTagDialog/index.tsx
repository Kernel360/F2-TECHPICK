'use client';

import { useRef, memo, KeyboardEvent, MouseEvent } from 'react';
import * as Dialog from '@radix-ui/react-dialog';
import * as VisuallyHidden from '@radix-ui/react-visually-hidden';
import { useQueryClient } from '@tanstack/react-query';
import { notifyError } from '@/shared/lib';
import { Text, Button, Gap } from '@/shared/ui';
import { useTagStore } from '@/entities/tag';
import { useDeleteTagDialogStore } from '../../deleteTag.model';
import { dialogContentStyle, dialogOverlayStyle } from './DeleteTagDialog.css';

export const DeleteTagDialog = memo(function DeleteTagDialog() {
  const cancelButtonRef = useRef<HTMLButtonElement | null>(null);
  const { deleteTag } = useTagStore();
  const { deleteTagId, isOpen, setIsOpen } = useDeleteTagDialogStore();
  const queryClient = useQueryClient();

  const closeDialog = () => {
    setIsOpen(false);
  };

  const closeDialogByEnterKey = (event: KeyboardEvent<HTMLButtonElement>) => {
    event.stopPropagation();

    if (event.key === 'Enter') {
      closeDialog();
    }
  };

  const handleDeleteTag = async () => {
    if (!deleteTagId) {
      return;
    }

    try {
      closeDialog();
      await deleteTag(deleteTagId);
    } catch (error) {
      if (error instanceof Error) {
        notifyError(error.message);
      }
    }
  };

  const DeleteTagByClick = async (e: MouseEvent<HTMLButtonElement>) => {
    e.stopPropagation();
    await handleDeleteTag();
    queryClient.invalidateQueries({ queryKey: ['pick'] });
  };

  const DeleteTagByEnterKey = async (e: KeyboardEvent<HTMLButtonElement>) => {
    e.stopPropagation();

    if (e.key === 'Enter') {
      await handleDeleteTag();
    }
  };

  return (
    <Dialog.Root open={isOpen} onOpenChange={setIsOpen}>
      <Dialog.Portal container={document.querySelector('#portalContainer')}>
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
              onClick={DeleteTagByClick}
              onKeyDown={DeleteTagByEnterKey}
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
                onKeyDown={closeDialogByEnterKey}
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
});
