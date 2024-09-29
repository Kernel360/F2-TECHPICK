import React from 'react';
import {
  linkViewSection,
  directoryTree,
  folderViewSection,
  linkEditorLabel,
  linkEditorSectionFooter,
  linkEditorHeader,
  linkEditorSection,
  linkEditor,
  searchSection,
} from './linkEditorSection.css';
import Image from 'next/image';
import { DirectoryNode } from '@/components/DirectoryNode/DirectoryNode';
import { NodeApi, Tree } from 'react-arborist';
import { NodeData } from '@/shared/types/NodeData';
import { useDragDropManager } from 'react-dnd';

interface LinkEditorSectionProps {
  focusedNode: NodeApi<NodeData> | null;
  focusedNodeFolderData: NodeData[] | undefined;
  focusedNodeLinkData: NodeData[] | undefined;
}

export function LinkEditorSection({
  focusedNode,
  focusedNodeFolderData,
  focusedNodeLinkData,
}: LinkEditorSectionProps) {
  const dragDropManager = useDragDropManager();

  return (
    <div className={linkEditorSection}>
      <div className={linkEditorHeader}>
        <div className={linkEditorLabel}>
          <div>React</div>
          <div>&gt;</div>
          <div>TypeScript</div>
        </div>

        <div className={searchSection}>
          <div>
            <Image
              src={`image/ic_sort.svg`}
              alt="search"
              width={24}
              height={24}
            />
          </div>
          <div>
            <Image
              src={`image/ic_search.svg`}
              alt="search"
              width={24}
              height={24}
            />
          </div>
        </div>
      </div>
      <div className={linkEditor}>
        <div className={folderViewSection}>
          {focusedNode && (
            <Tree
              className={directoryTree}
              data={focusedNodeFolderData}
              openByDefault={false}
              rowHeight={32}
              indent={24}
              overscanCount={1}
              dndManager={dragDropManager}
            >
              {DirectoryNode}
            </Tree>
          )}
        </div>
        <div className={linkViewSection}>
          {focusedNode && (
            <Tree
              className={directoryTree}
              data={focusedNodeLinkData}
              openByDefault={false}
              rowHeight={32}
              indent={24}
              overscanCount={1}
              dndManager={dragDropManager}
            >
              {DirectoryNode}
            </Tree>
          )}
        </div>
      </div>
      <div className={linkEditorSectionFooter}></div>
    </div>
  );
}
