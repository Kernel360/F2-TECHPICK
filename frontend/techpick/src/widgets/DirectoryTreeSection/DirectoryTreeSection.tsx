import React from 'react';
import {
  dirContainer,
  dirHeader,
  dirHeaderWrapper,
  dirRow,
  dirTree,
  leftFooter,
  leftHeader,
  leftSection,
  logo,
  logoContainer,
  treeWrapper,
} from './directoryTreeSection.css';
import Image from 'next/image';
import { ToggleTheme } from '@/features/';
import { NodeData } from '@/shared/types/NodeData';
import { NodeApi, Tree } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { mockData } from '@/shared/const/mockdata';
import { DirectoryNode } from '@/components';

interface LeftSectionProps {
  setFocusedNode: (node: NodeApi<NodeData>) => void;
}

export function DirectoryTreeSection({ setFocusedNode }: LeftSectionProps) {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();

  return (
    <div className={leftSection}>
      <div className={leftHeader}>
        <div className={logoContainer}>
          <Image
            src={`image/ic_user.svg`}
            width={32}
            height={32}
            alt="profile"
          />
          <div className={logo}>TechPick</div>
        </div>
        <ToggleTheme />
      </div>
      <div className={dirContainer}>
        <div className={dirHeaderWrapper}>
          <Image
            src={`image/ic_directory.svg`}
            width={20}
            height={20}
            alt="directory"
          />
          <div className={dirHeader}>Directory</div>
        </div>
        <div className={treeWrapper} ref={ref}>
          <Tree
            className={dirTree}
            rowClassName={dirRow}
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
      <div className={leftFooter}></div>
    </div>
  );
}
