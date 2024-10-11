import React, { useRef } from 'react';
import {
  directoryLabel,
  directoryLabelContainer,
  directoryTree,
  directoryTreeContainer,
  directoryTreeSectionFooter,
  directoryTreeWrapper,
  leftSidebarSection,
  logo,
  profileContainer,
  profileSection,
} from './directoryTreeSection.css';
import Image from 'next/image';
import { ToggleThemeButton } from '@/features/';
import { NodeData } from '@/shared/types/NodeData';
import { NodeApi, Tree, TreeApi } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { useDragDropManager } from 'react-dnd';
import { useTreeStore } from '@/shared/stores/treeStore';
import { Folder } from 'lucide-react';
import { EditorContextMenu } from '../EditorContextMenu';
import { useGetStructure } from '@/features/folderManagement/hooks/useGetStructure';
import { useTreeHandlers } from '@/features/folderManagement/hooks/useTreeHandlers';
import { DirectoryNode } from '@/widgets/DirectoryNode/DirectoryNode';

export function DirectoryTreeSection() {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();
  const treeRef = useRef<TreeApi<NodeData> | undefined>(undefined);
  const dragDropManager = useDragDropManager();
  const { setTreeRef, setFocusedNode } = useTreeStore();
  const { handleCreate, handleDrag, handleRename, handleDelete } =
    useTreeHandlers();

  const handleTreeRef = (instance: TreeApi<NodeData> | null | undefined) => {
    if (instance && !treeRef.current) {
      treeRef.current = instance;
      setTreeRef(treeRef);
    }
  };

  const {
    data: structureData,
    error: structureError,
    isLoading: isStructureLoading,
  } = useGetStructure();

  return (
    <div className={leftSidebarSection}>
      <div className={profileSection}>
        <div className={profileContainer}>
          <Image
            src={`image/ic_user.svg`}
            width={32}
            height={32}
            alt="profile"
          />
          <div className={logo}>TechPick</div>
        </div>
        <ToggleThemeButton />
      </div>
      <div className={directoryTreeContainer}>
        <div className={directoryLabelContainer}>
          <Folder size={20} strokeWidth={1} />
          <div className={directoryLabel}>Directory</div>
          <button
            onClick={() => {
              treeRef.current?.createInternal();
            }}
          >
            +Folder
          </button>
          <button
            onClick={() => {
              treeRef.current?.createLeaf();
            }}
          >
            +Pick
          </button>
        </div>
        <div className={directoryTreeWrapper} ref={ref}>
          {isStructureLoading && <div>Loading...</div>}
          {structureError && <div>Error: {structureError.message}</div>}
          {!isStructureLoading && !structureError && (
            <EditorContextMenu>
              <Tree
                ref={handleTreeRef}
                className={directoryTree}
                data={structureData?.root}
                disableMultiSelection={true}
                onFocus={(node: NodeApi) => {
                  setFocusedNode(node);
                }}
                onMove={handleDrag}
                onCreate={handleCreate}
                onRename={handleRename}
                onDelete={handleDelete}
                openByDefault={false}
                width={width}
                height={height && height - 8}
                rowHeight={32}
                indent={24}
                overscanCount={1}
                dndManager={dragDropManager}
              >
                {DirectoryNode}
              </Tree>
            </EditorContextMenu>
          )}
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
