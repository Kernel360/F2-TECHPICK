import React from 'react';
import * as ContextMenu from '@radix-ui/react-context-menu';

import {
  ContextMenuTrigger,
  ContextMenuContent,
  ContextMenuItem,
  RightSlot,
} from './ContextMenu.css';
import { useTreeStore } from '@/shared/stores/treeStore';
import { getCurrentTreeTypeByNode } from '@/features/nodeManagement/utils/getCurrentTreeTypeByNode';
import { useRestoreNode } from '@/features/nodeManagement/hooks/useRestoreNode';
import toast from 'react-hot-toast';

interface ContextMenuWrapperProps {
  children: React.ReactNode;
}

export function TreeContextMenu({ children }: ContextMenuWrapperProps) {
  const { treeRef, focusedNode } = useTreeStore();
  const portalContainer = document.getElementById('portalContainer');
  const { restoreNode } = useRestoreNode();

  const currentTree =
    focusedNode && getCurrentTreeTypeByNode(focusedNode, treeRef);

  return (
    <ContextMenu.Root>
      <ContextMenu.Trigger className={ContextMenuTrigger}>
        {children}
      </ContextMenu.Trigger>
      <ContextMenu.Portal container={portalContainer}>
        <ContextMenu.Content className={ContextMenuContent}>
          {focusedNode?.data.type === 'folder' && currentTree === 'root' && (
            <ContextMenu.Item
              className={ContextMenuItem}
              onClick={() => {
                treeRef.rootRef.current!.createInternal();
              }}
            >
              새 폴더 <div className={RightSlot}></div>
            </ContextMenu.Item>
            // <ContextMenu.Sub>
            //   <ContextMenu.SubTrigger className={ContextMenuSubTrigger}>
            //     새로 만들기
            //     <div className={RightSlot}>
            //       <ChevronRightIcon />
            //     </div>
            //   </ContextMenu.SubTrigger>
            //   <ContextMenu.Portal container={portalContainer}>
            //     <ContextMenu.SubContent
            //       className={ContextMenuSubContent}
            //       sideOffset={2}
            //       alignOffset={-5}
            //     >
            //       <ContextMenu.Item
            //         className={ContextMenuItem}
            //         onClick={() => {
            //           treeRef.rootRef.current!.createInternal();
            //         }}
            //       >
            //         Folder <div className={RightSlot}></div>
            //       </ContextMenu.Item>
            //
            //       <ContextMenu.Item
            //         className={ContextMenuItem}
            //         onClick={() => {
            //           treeRef.rootRef.current!.createLeaf();
            //         }}
            //       >
            //         Pick
            //       </ContextMenu.Item>
            //     </ContextMenu.SubContent>
            //   </ContextMenu.Portal>
            // </ContextMenu.Sub>
          )}
          {currentTree === 'root' && (
            <ContextMenu.Item
              className={ContextMenuItem}
              onClick={() => {
                focusedNode!.edit();
              }}
            >
              이름 바꾸기 <div className={RightSlot}></div>
            </ContextMenu.Item>
          )}
          {currentTree === 'recycleBin' && (
            <ContextMenu.Item
              className={ContextMenuItem}
              onClick={() => {
                restoreNode({
                  ids: [focusedNode!.id],
                  nodes: [focusedNode!],
                });
                toast('성공적으로 복원되었습니다!');
              }}
            >
              복원 <div className={RightSlot}></div>
            </ContextMenu.Item>
          )}
          <ContextMenu.Item
            className={ContextMenuItem}
            onClick={() => {
              if (currentTree === 'root') {
                treeRef.rootRef.current!.delete(focusedNode!.id);
                toast('휴지통으로 이동되었습니다.');
              } else {
                treeRef.recycleBinRef.current!.delete(focusedNode!.id);
                toast('완전히 삭제되었습니다.');
              }
            }}
          >
            삭제
            <div className={RightSlot}></div>
          </ContextMenu.Item>
        </ContextMenu.Content>
      </ContextMenu.Portal>
    </ContextMenu.Root>
  );
}
