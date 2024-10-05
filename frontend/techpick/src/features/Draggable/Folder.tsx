import React from 'react';
import Image from 'next/image';
import { folderWrapper } from '@/features/Draggable/folder.css';
import { NodeApi } from 'react-arborist';
import { useDragHook } from '@/hooks/useDragHook';
import { useTreeStore } from '@/shared/stores/treeStore';

export function Folder({ node }: { node: NodeApi }) {
  const ref = useDragHook(node);
  const { setFocusedNode } = useTreeStore();

  return (
    <div
      ref={ref as unknown as React.LegacyRef<HTMLDivElement>}
      className={folderWrapper}
      onClick={() => {
        node.tree.open(node.id);
        setFocusedNode(node);
        node.tree.select(node.id);
      }}
    >
      <Image src={`image/ic_folder.svg`} alt="folder" width={64} height={64} />
      {node.data.name}
    </div>
  );
}
