'use client';

import {
  DirectoryTreeSection,
  LinkEditorSection,
  FeaturedSection,
} from '@/widgets';
import { rootLayout } from '@/app/style.css';
import { viewContainer, viewWrapper } from './style.css';
import { NodeData } from '@/shared/types';
import React, { useMemo } from 'react';
import { NodeApi } from 'react-arborist';

export default function MainPage() {
  const [focusedNode, setFocusedNode] =
    React.useState<NodeApi<NodeData> | null>(null);

  const [focusedNodeFolderData, focusedNodeLinkData] = useMemo(() => {
    if (!focusedNode || !focusedNode.data.children) {
      return [];
    }

    const arrFolder: NodeData[] = [];
    const arrLink: NodeData[] = [];

    focusedNode.data.children.forEach((node) => {
      if (node.type === 'folder') {
        arrFolder.push(node);
      } else if (node.type === 'link') {
        arrLink.push(node);
      }
    });

    return [arrFolder, arrLink];
  }, [focusedNode]);

  return (
    <div className={rootLayout}>
      <div className={viewWrapper}>
        <div className={viewContainer}>
          <DirectoryTreeSection setFocusedNode={setFocusedNode} />
          <LinkEditorSection
            focusedNode={focusedNode}
            focusedNodeFolderData={focusedNodeFolderData}
            focusedNodeLinkData={focusedNodeLinkData}
          />
          <FeaturedSection />
        </div>
      </div>
    </div>
  );
}
