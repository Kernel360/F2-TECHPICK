import React from 'react';
import * as ContextMenu from '@radix-ui/react-context-menu';
import { ChevronRightIcon } from '@radix-ui/react-icons';

import {
  ContextMenuTrigger,
  ContextMenuContent,
  ContextMenuItem,
  RightSlot,
  ContextMenuSubContent,
  ContextMenuSubTrigger,
} from './ContextMenu.css';
import { useTreeStore } from '@/shared/stores/treeStore';
import { getCurrentTreeTypeByNode } from '@/features/nodeManagement/utils/getCurrentTreeTypeByNode';
import { useTreeHandlers } from '@/features/nodeManagement/hooks/useTreeHandlers';

interface ContextMenuWrapperProps {
  children: React.ReactNode;
}

export function EditorContextMenu({ children }: ContextMenuWrapperProps) {
  const { treeRef, focusedNode } = useTreeStore();
  const portalContainer = document.getElementById('portalContainer');
  const { handleRestore } = useTreeHandlers();

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
            <ContextMenu.Sub>
              <ContextMenu.SubTrigger className={ContextMenuSubTrigger}>
                New
                <div className={RightSlot}>
                  <ChevronRightIcon />
                </div>
              </ContextMenu.SubTrigger>
              <ContextMenu.Portal container={portalContainer}>
                <ContextMenu.SubContent
                  className={ContextMenuSubContent}
                  sideOffset={2}
                  alignOffset={-5}
                >
                  <ContextMenu.Item
                    className={ContextMenuItem}
                    onClick={() => {
                      treeRef.rootRef.current!.createInternal();
                    }}
                  >
                    Folder <div className={RightSlot}></div>
                  </ContextMenu.Item>

                  <ContextMenu.Item
                    className={ContextMenuItem}
                    onClick={() => {
                      treeRef.rootRef.current!.createLeaf();
                    }}
                  >
                    Pick
                  </ContextMenu.Item>
                </ContextMenu.SubContent>
              </ContextMenu.Portal>
            </ContextMenu.Sub>
          )}
          {currentTree === 'root' && (
            <ContextMenu.Item
              className={ContextMenuItem}
              onClick={() => {
                focusedNode!.edit();
              }}
            >
              Rename <div className={RightSlot}></div>
            </ContextMenu.Item>
          )}
          {currentTree === 'recycleBin' && (
            <ContextMenu.Item
              className={ContextMenuItem}
              onClick={() => {
                handleRestore({
                  ids: [focusedNode!.id],
                  nodes: [focusedNode!],
                });
              }}
            >
              Restore <div className={RightSlot}></div>
            </ContextMenu.Item>
          )}
          <ContextMenu.Item
            className={ContextMenuItem}
            onClick={() => {
              if (currentTree === 'root') {
                treeRef.rootRef.current!.delete(focusedNode!.id);
              } else {
                treeRef.recycleBinRef.current!.delete(focusedNode!.id);
              }
            }}
          >
            Delete
            <div className={RightSlot}></div>
          </ContextMenu.Item>
        </ContextMenu.Content>
      </ContextMenu.Portal>
    </ContextMenu.Root>
  );
}
