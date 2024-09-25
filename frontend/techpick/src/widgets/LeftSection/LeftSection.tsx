import React, { FC } from 'react';
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
} from '@/widgets/DirView/dirview.css';
import IcProfile from '@/lib/icons/ic_user2.svg';
import ToggleTheme from '@/components/ToggleTheme/ToggleTheme';
import IcDirectory from '@/lib/icons/ic_directory.svg';
import { NodeData } from '@/lib/types/NodeData';
import { NodeApi, Tree } from 'react-arborist';
import Image from 'next/image';
import useResizeObserver from 'use-resize-observer';
import { mockData } from '@/lib/const/mockdata';
import { DirNode } from '@/components/DirNode/DirNode';

interface LeftSectionProps {
  setFocusedNode: (node: NodeApi<NodeData>) => void;
}

const LeftSection: FC<LeftSectionProps> = ({ setFocusedNode }) => {
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
            // renderRow={DirRow}
            // renderDragPreview={DirDragPreview}
            // renderCursor={DirCursor}
            openByDefault={false}
            width={width}
            height={height && height - 8}
            rowHeight={32}
            indent={24}
            overscanCount={1}
          >
            {DirNode}
          </Tree>
        </div>
      </div>
      <div className={leftFooter}></div>
    </div>
  );
};

export default LeftSection;
