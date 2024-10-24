import React from 'react';
import Image from 'next/image';
import { NodeApi } from 'react-arborist';
import { useDragHook } from '@/components/nodeManagement/hooks/useDragHook';
import { linkWrapper } from '@/components/nodeManagement/ui/pick.css';

// 여기에 PickCard카드될 예정입니다.
export function Pick({ node }: { node: NodeApi }) {
  const ref = useDragHook(node);

  return (
    <div
      ref={ref as unknown as React.LegacyRef<HTMLDivElement>}
      className={linkWrapper}
    >
      {/* Todo 카드 하나 렌더링*/}
      <Image
        src={`image/ic_doc.svg`}
        alt={`${node.data.name}'s image`}
        width={64}
        height={64}
      />
      {node.data.name}
    </div>
  );
}
