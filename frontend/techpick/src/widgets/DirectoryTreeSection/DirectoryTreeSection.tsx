import React from 'react';
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
import { mockData } from '@/shared/const/mockdata';
import { DirectoryNode } from '@/components';

interface DirectoryTreeSectionProps {
  setFocusedNode: (node: NodeApi<NodeData>) => void;
}

export function DirectoryTreeSection({
  setFocusedNode,
}: DirectoryTreeSectionProps) {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();

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
            data={mockData}
            onFocus={(node: NodeApi<NodeData>) => {
              setFocusedNode(node);
            }}
            openByDefault={false}
            width={width}
            height={height && height - 8}
            rowHeight={32}
            indent={24}
            overscanCount={1}
          >
            {DirectoryNode}
          </Tree>
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
