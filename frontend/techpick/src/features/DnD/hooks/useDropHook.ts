import { RefObject } from 'react';
import { ConnectDropTarget, useDrop } from 'react-dnd';
import { DragItem } from 'react-arborist/dist/main/types/dnd';
import { computeDrop } from 'react-arborist/dist/main/dnd/compute-drop';
import { actions as dnd } from 'react-arborist/dist/main/state/dnd-slice';
import { NodeApi } from 'react-arborist';
import { NodeApi as ArboristNodeApi } from 'react-arborist/dist/main/interfaces/node-api';

export type DropResult = {
  parentId: string | null;
  index: number | null;
};

export function useDropHook(
  el: RefObject<HTMLElement | null>,
  node: NodeApi
): ConnectDropTarget {
  const tree = node.tree;
  const [_, dropRef] = useDrop<DragItem, DropResult | null, void>(
    () => ({
      accept: 'NODE',
      canDrop: () => tree.canDrop(),
      hover: (_item, m) => {
        const offset = m.getClientOffset();
        if (!el.current || !offset) return;
        const { cursor, drop } = computeDrop({
          element: el.current,
          offset: offset,
          indent: tree.indent,
          node: node as unknown as ArboristNodeApi,
          prevNode: node.prev as unknown as ArboristNodeApi,
          nextNode: node.next as unknown as ArboristNodeApi,
        });
        if (drop) tree.dispatch(dnd.hovering(drop.parentId, drop.index));

        if (m.canDrop()) {
          if (cursor) tree.showCursor(cursor);
        } else {
          tree.hideCursor();
        }
      },
      drop: (_, m) => {
        if (!m.canDrop()) return null;
      },
    }),
    [node, el.current, tree.props]
  );

  return dropRef;
}
