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
} from './contextMenu.css';
import { useTreeStore } from '@/shared/stores/treeStore';

interface ContextMenuWrapperProps {
  children: React.ReactNode;
}

export function EditorContextMenu({ children }: ContextMenuWrapperProps) {
  const { treeRef } = useTreeStore();
  const focusedNode = treeRef.current?.focusedNode;

  return (
    <ContextMenu.Root>
      <ContextMenu.Trigger className={ContextMenuTrigger}>
        {children}
      </ContextMenu.Trigger>
      <ContextMenu.Portal>
        <ContextMenu.Content className={ContextMenuContent}>
          {focusedNode?.data.type === 'folder' && (
            <ContextMenu.Sub>
              <ContextMenu.SubTrigger className={ContextMenuSubTrigger}>
                New
                <div className={RightSlot}>
                  <ChevronRightIcon />
                </div>
              </ContextMenu.SubTrigger>
              <ContextMenu.Portal>
                <ContextMenu.SubContent
                  className={ContextMenuSubContent}
                  sideOffset={2}
                  alignOffset={-5}
                >
                  <ContextMenu.Item
                    className={ContextMenuItem}
                    onClick={() => {
                      treeRef.current!.createInternal();
                    }}
                  >
                    Folder <div className={RightSlot}></div>
                  </ContextMenu.Item>

                  <ContextMenu.Item
                    className={ContextMenuItem}
                    onClick={() => {
                      treeRef.current!.createLeaf();
                    }}
                  >
                    Pick
                  </ContextMenu.Item>
                </ContextMenu.SubContent>
              </ContextMenu.Portal>
            </ContextMenu.Sub>
          )}

          <ContextMenu.Item
            className={ContextMenuItem}
            onClick={() => {
              focusedNode!.edit();
              console.log(
                'EditIng Mode - focusedNode:',
                treeRef.current!.focusedNode
              );
            }}
          >
            Rename <div className={RightSlot}></div>
          </ContextMenu.Item>
          <ContextMenu.Item
            className={ContextMenuItem}
            onClick={() => {
              treeRef.current!.delete(focusedNode!.id);
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
