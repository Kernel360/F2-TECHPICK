'use client';

import {
  DirectoryTreeSection,
  LinkEditorSection,
  FeaturedSection,
} from '@/widgets';
import { rootLayout } from '@/app/style.css';
import { viewContainer, viewWrapper } from './style.css';
import React, { useMemo } from 'react';
import { NodeApi } from 'react-arborist';

export default function MainPage() {
  const [focusedNode, setFocusedNode] = React.useState<NodeApi>();

  const [focusedNodeFolderData, focusedNodeLinkData] = useMemo(() => {
    if (!focusedNode || !focusedNode.data.children) {
      return [];
    }
    console.log('data updated!!!!!!!!');
    console.log('focusedNode', focusedNode);
    console.log('children', focusedNode.children);
    const FolderNodeList: NodeApi[] = [];
    const LinkNodeList: NodeApi[] = [];

    focusedNode.children?.forEach((node) => {
      if (node.data.type === 'folder') {
        FolderNodeList.push(node);
      } else if (node.data.type === 'link') {
        LinkNodeList.push(node);
      }
    });

    return [FolderNodeList, LinkNodeList];
  }, [focusedNode]);

  return (
    <div className={rootLayout}>
      <div className={viewWrapper}>
        <div className={viewContainer}>
          <DirectoryTreeSection setFocusedNode={setFocusedNode} />
          <LinkEditorSection
            focusedNode={focusedNode}
            focusedNodeFolder={focusedNodeFolderData}
            focusedNodeLink={focusedNodeLinkData}
          />
          <FeaturedSection />
        </div>
      </div>
    </div>
  );
}
