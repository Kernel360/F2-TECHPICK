import React, { useState } from 'react';
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
import { NodeApi, Tree } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { dynamicMockData } from '@/shared/const/mockdata';
import { DirectoryNode } from '@/components';
import { useDragDropManager } from 'react-dnd';
import { moveNode } from '@/features/moveNode';

interface DirectoryTreeSectionProps {
  setFocusedNode: (node: NodeApi<NodeData>) => void;
}

export function DirectoryTreeSection({
  setFocusedNode,
}: DirectoryTreeSectionProps) {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();
  const dragDropManager = useDragDropManager();
  const [treeData, setTreeData] = useState<NodeData[]>(dynamicMockData);

  interface MoveArgs {
    dragIds: string[];
    parentId: string | null;
    index: number;
  }

  const handleMove = ({ dragIds, parentId, index }: MoveArgs) => {
    const dragId = dragIds[0];

    const updatedData = moveNode(treeData, dragId, parentId, index);
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
          <Image
            src={`image/ic_directory.svg`}
            width={20}
            height={20}
            alt="directory"
          />
          <div className={directoryLabel}>Directory</div>
        </div>
        <div className={directoryTreeWrapper} ref={ref}>
          <Tree
            className={directoryTree}
            data={treeData}
            onFocus={(node: NodeApi<NodeData>) => {
              setFocusedNode(node);
            }}
            openByDefault={false}
            width={width}
            height={height && height - 8}
            rowHeight={32}
            indent={24}
            overscanCount={1}
            dndManager={dragDropManager}
            onMove={handleMove}
          >
            {DirectoryNode}
          </Tree>
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
