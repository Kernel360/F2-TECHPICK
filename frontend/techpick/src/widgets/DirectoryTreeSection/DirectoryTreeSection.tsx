import React, { useRef } from 'react';
import {
  directoryTreeContainer,
  directoryLabel,
  directoryLabelContainer,
  directoryTree,
  directoryTreeSectionFooter,
  profileSection,
  leftSidebarSection,
  logo,
  profileContainer,
  directoryTreeWrapper,
} from './directoryTreeSection.css';
import Image from 'next/image';
import { ToggleThemeButton } from '@/features/';
import { NodeData } from '@/shared/types/NodeData';
import { NodeApi, Tree, MoveHandler, TreeApi } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { DirectoryNode } from '@/components';
import { useDragDropManager } from 'react-dnd';
import { moveNode } from '@/features/moveNode';
import { useTreeStore } from '@/shared/stores/treeStore';
import { Folder } from 'lucide-react';
import { EditorContextMenu } from '@/widgets/ContextMenuWrapper';

export function DirectoryTreeSection() {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();
  const treeRef = useRef<TreeApi<NodeData> | undefined>(undefined);
  const dragDropManager = useDragDropManager();
  const {
    treeData,
    focusedNode,
    focusedFolderNodeList,
    focusedLinkNodeList,
    setTreeRef,
    setFocusedFolderNodeList,
    setFocusedLinkNodeList,
    setTreeData,
    setFocusedNode,
  } = useTreeStore();

  const handleTreeRef = (instance: TreeApi<NodeData> | null | undefined) => {
    if (instance && !treeRef.current) {
      treeRef.current = instance;
      setTreeRef(treeRef);
    }
  };

  const handleMove: MoveHandler<NodeData> = ({
    dragIds,
    dragNodes,
    parentId,
    parentNode,
    index,
  }) => {
    const updatedData = moveNode(
      treeData,
      focusedNode,
      focusedFolderNodeList,
      focusedLinkNodeList,
      setFocusedFolderNodeList,
      setFocusedLinkNodeList,
      dragIds[0],
      dragNodes[0],
      parentId,
      parentNode,
      index
    );

    setTreeData(updatedData);
  };

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
        </div>
        <div className={directoryTreeWrapper} ref={ref}>
          <EditorContextMenu>
            <Tree
              ref={handleTreeRef}
              className={directoryTree}
              data={treeData}
              disableMultiSelection={true}
              onFocus={(node: NodeApi) => {
                setFocusedNode(node);
              }}
              onMove={handleMove}
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
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
