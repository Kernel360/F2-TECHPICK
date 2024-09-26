import React from 'react';
import {
  bookmarkContainer,
  dirRow,
  dirTree,
  folderContainer,
  locationContainer,
  middleFooter,
  middleHeader,
  middleSection,
  middleView,
  topButtonContainer,
} from './middleSection.css';
import IcSort from '@/lib/icons/ic_sort.svg';
import IcSearch from '@/lib/icons/ic_search.svg';
import { DirectoryNode } from '@/components/DirectoryNode/DirectoryNode';
import Image from 'next/image';
import { NodeApi, Tree } from 'react-arborist';
import { NodeData } from '@/shared/types/NodeData';

interface MiddleSectionProps {
  focusedNode: NodeApi<NodeData> | null;
  focusedNodeFolderData: NodeData[] | undefined;
  focusedNodeLinkData: NodeData[] | undefined;
}

export default function MiddleSection({
  focusedNode,
  focusedNodeFolderData,
  focusedNodeLinkData,
}: MiddleSectionProps) {
  return (
    <div className={middleSection}>
      <div className={middleHeader}>
        <div className={locationContainer}>
          <div>React</div>
          <div>&gt;</div>
          <div>TypeScript</div>
        </div>

        <div className={topButtonContainer}>
          <div>
            <Image src={IcSort} alt="search" width={24} />
          </div>
          <div>
            <Image src={IcSearch} alt="search" width={24} />
          </div>
        </div>
      </div>
      <div className={middleView}>
        <div className={folderContainer}>
          {focusedNode && (
            <Tree
              className={dirTree}
              rowClassName={dirRow}
              data={focusedNodeFolderData}
              openByDefault={false}
              rowHeight={32}
              indent={24}
              overscanCount={1}
            >
              {DirectoryNode}
            </Tree>
          )}
        </div>
        <div className={bookmarkContainer}>
          {focusedNode && (
            <Tree
              className={dirTree}
              rowClassName={dirRow}
              data={focusedNodeLinkData}
              openByDefault={false}
              rowHeight={32}
              indent={24}
              overscanCount={1}
            >
              {DirectoryNode}
            </Tree>
          )}
        </div>
      </div>
      <div className={middleFooter}></div>
    </div>
  );
}
