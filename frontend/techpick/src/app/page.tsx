'use client';

import {
  DirectoryTreeSection,
  LinkEditorSection,
  FeaturedSection,
} from '@/widgets';
import { rootLayout } from '@/app/style.css';
import { viewContainer, viewWrapper } from './style.css';
import React, { useEffect, useMemo } from 'react';
import { NodeApi } from 'react-arborist';
import { useTreeStore } from '@/shared/stores/treeStore';

export default function MainPage() {
  const {
    focusedNode,
    focusedFolderNodeList,
    focusedLinkNodeList,
    setFocusedFolderNodeList,
    setFocusedLinkNodeList,
  } = useTreeStore();

  const [tempFocusedFolderList, tempFocusedPickList] = useMemo(() => {
    if (!focusedNode || !focusedNode.children?.length) {
      return [[], []];
    }
    const folderList: NodeApi[] = [];
    const linkList: NodeApi[] = [];

    focusedNode.children?.forEach((node) => {
      if (node.data.type === 'folder') {
        folderList.push(node);
      } else if (node.data.type === 'pick') {
        linkList.push(node);
      }
    });

    return [folderList, linkList];
  }, [focusedNode]);

  useEffect(() => {
    setFocusedFolderNodeList(tempFocusedFolderList);
    setFocusedLinkNodeList(tempFocusedPickList);
  }, [
    tempFocusedFolderList,
    tempFocusedPickList,
    setFocusedFolderNodeList,
    setFocusedLinkNodeList,
  ]);

  return (
    <div className={rootLayout}>
      <div className={viewWrapper}>
        <div className={viewContainer}>
          <DirectoryTreeSection />
          <LinkEditorSection
            focusedNode={focusedNode}
            focusedNodeFolder={focusedFolderNodeList}
            focusedNodeLink={focusedLinkNodeList}
          />
          <FeaturedSection />
        </div>
      </div>
    </div>
  );
}
