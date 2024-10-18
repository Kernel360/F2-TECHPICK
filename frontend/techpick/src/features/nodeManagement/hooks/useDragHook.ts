import { NodeApi } from 'react-arborist';
import { ConnectDragSource, useDrag } from 'react-dnd';
import { DragItem } from 'react-arborist/dist/main/types/dnd';
import { actions as dnd } from 'react-arborist/dist/main/state/dnd-slice';
import { DropResult } from 'react-arborist/dist/main/dnd/drop-hook';
import { safeRun } from 'react-arborist/dist/main/utils';
import { ROOT_ID } from 'react-arborist/dist/main/data/create-root';
import { useEffect } from 'react';
import { getEmptyImage } from 'react-dnd-html5-backend';
import { useTreeStore } from '@/shared/stores/treeStore';

export function useDragHook(node: NodeApi): ConnectDragSource {
  const { treeRef } = useTreeStore();
  const tree = treeRef.rootRef.current!;

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

  return ref;
}
