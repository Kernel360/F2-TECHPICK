import React, { useEffect } from 'react';
import { useDrag } from 'react-dnd';
import Image from 'next/image';
import { folderWrapper } from '@/features/Draggable/folder.css';
import { NodeApi } from 'react-arborist';
import { DragItem } from 'react-arborist/dist/main/types/dnd';
import { DropResult } from 'react-arborist/dist/main/dnd/drop-hook';
import { actions as dnd } from 'react-arborist/dist/main/state/dnd-slice';
import { ROOT_ID } from 'react-arborist/dist/main/data/create-root';
import { safeRun } from 'react-arborist/dist/main/utils';
import { getEmptyImage } from 'react-dnd-html5-backend';

export function Folder({ node }: { node: NodeApi }) {
  const tree = node.tree;

  const [_, ref, preview] = useDrag<DragItem, DropResult, void>(
    () => ({
      canDrag: () => node.isDraggable,
      type: 'NODE',
      item: () => {
        const dragIds = [node.id];
        tree.store.dispatch(dnd.dragStart(node.id, dragIds));
        return { id: node.id };
      },
      end: () => {
        tree.hideCursor();
        const { parentId, index, dragIds } = tree.store.getState().dnd;

        console.log('dnd', tree.store.getState().dnd);
        if (tree.canDrop()) {
          safeRun(tree.props.onMove, {
            dragIds,
            parentId: parentId === ROOT_ID ? null : parentId,
            index: index === null ? 0 : index,
            dragNodes: [node], // 현재 한 개만 드래그하도록 구현
            parentNode: tree.get(parentId),
          });
          tree.open(parentId);
        }
        tree.store.dispatch(dnd.dragEnd());
      },
    }),
    [node]
  );

  useEffect(() => {
    preview(getEmptyImage());
  }, [preview]);

  return (
    <div
      ref={ref as unknown as React.LegacyRef<HTMLDivElement>}
      className={folderWrapper}
      onClick={() => {
        console.log('clicked!!!', node);
      }}
    >
      <Image src={`image/ic_folder.svg`} alt="folder" width={64} height={64} />
      {node.data.name}
    </div>
  );
}
