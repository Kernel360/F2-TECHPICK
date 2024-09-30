import React, { useEffect, useRef } from 'react';
import { useDrag } from 'react-dnd';
import Image from 'next/image';
import { NodeData } from '@/shared/types';
import { folderWrapper } from '@/features/Draggable/folder.css';

export function Folder({ item }: { item: NodeData }) {
  const [{ isDragging }, drag, dragPreview] = useDrag(() => ({
    type: item.type,
    item: { id: item.id },
    collect: (monitor) => ({
      isDragging: monitor.isDragging(),
    }),
  }));

  const ref = useRef<HTMLDivElement>(null);

  useEffect(() => {
    if (ref.current) {
      drag(ref.current);
      dragPreview(ref.current);
    }
  }, [drag, dragPreview]);

  return (
    <div
      ref={ref}
      className={folderWrapper}
      style={{ opacity: isDragging ? 0.5 : 1 }}
    >
      <Image src={`image/ic_folder.svg`} alt="folder" width={64} height={64} />
      {item.name}
    </div>
  );
}
