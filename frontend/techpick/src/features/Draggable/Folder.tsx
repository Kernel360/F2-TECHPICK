import React from 'react';
import Image from 'next/image';
import { folderWrapper } from '@/features/Draggable/folder.css';
import { NodeApi } from 'react-arborist';
import { useDragHook } from '@/hooks/useDragHook';

export function Folder({ node }: { node: NodeApi }) {
  const ref = useDragHook(node);

  return (
    <div
      ref={ref as unknown as React.LegacyRef<HTMLDivElement>}
      className={folderWrapper}
    >
      <Image src={`image/ic_folder.svg`} alt="folder" width={64} height={64} />
      {node.data.name}
    </div>
  );
}
