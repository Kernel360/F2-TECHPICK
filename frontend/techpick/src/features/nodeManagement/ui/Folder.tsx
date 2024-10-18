import React from 'react';
import Image from 'next/image';
import {
  folderWrapper,
  folderWrapperFocused,
} from '@/features/nodeManagement/ui/folder.css';
import { NodeApi } from 'react-arborist';
import { useDragHook } from '@/features/nodeManagement/hooks/useDragHook';
import { useTreeStore } from '@/shared/stores/treeStore';

export function Folder({ node }: { node: NodeApi }) {
  const ref = useDragHook(node);
  const {
    setFocusedNode,
    focusedNodeInEditorSection,
    setFocusedNodeInEditorSection,
  } = useTreeStore();
  const isFocused = focusedNodeInEditorSection?.id === node.id;

  return (
    // <EditorContextMenu>
    <div
      ref={ref as unknown as React.LegacyRef<HTMLDivElement>}
      className={isFocused ? folderWrapperFocused : folderWrapper}
      onClick={() => {
        setFocusedNodeInEditorSection(node);
      }}
      onDoubleClick={() => {
        setFocusedNode(node);
      }}
      onContextMenu={() => {
        setFocusedNodeInEditorSection(node);
      }}
    >
      <Image
        src={`image/ic_folder.svg`}
        alt={`${node.data.name}'s image`}
        width={64}
        height={64}
      />
      {node.data.name}
    </div>
    // </EditorContextMenu>
  );
}
