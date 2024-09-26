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
} from './leftSection.css';
import Image from 'next/image';
import { IcProfile, IcDirectory } from '@/lib/icons';
import { ToggleTheme } from '@/features/';
import { NodeData } from '@/shared/types/NodeData';
import { NodeApi, Tree } from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { mockData } from '@/lib/const/mockdata';
import { DirectoryNode } from '@/components/DirectoryNode/DirectoryNode';

interface LeftSectionProps {
  setFocusedNode: (node: NodeApi<NodeData>) => void;
}

export function LeftSection({ setFocusedNode }: LeftSectionProps) {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();

  return (
    <div className={leftSection}>
      <div className={leftHeader}>
        <div className={logoContainer}>
          <Image src={IcProfile} width={32} alt="profile" />
          <div className={logo}>TechPick</div>
        </div>
        <ToggleTheme />
      </div>
      <div className={dirContainer}>
        <div className={dirHeaderWrapper}>
          <Image src={IcDirectory} width={20} alt="directory" />
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
