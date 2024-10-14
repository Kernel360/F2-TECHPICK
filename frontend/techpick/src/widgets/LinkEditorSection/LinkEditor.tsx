import React, { useCallback, useRef } from 'react';

import { Folder } from '@/features/DnD/Folder';
import { Pick } from '@/features/DnD/Pick';
import { useTreeStore } from '@/shared/stores/treeStore';
import { useDropHook } from '@/features/DnD/hooks/useDropHook';
import {
  folderViewSection,
  linkViewSection,
} from '@/widgets/LinkEditorSection/LinkEditorSection.css';

export const LinkEditor = () => {
  const { treeRef, focusedNode, focusedFolderNodeList, focusedLinkNodeList } =
    useTreeStore();
  const el = useRef<HTMLDivElement | null>(null);
  const dropRef = useDropHook(
    el,
    focusedNode ? focusedNode : treeRef.current!.root
  );

  const innerRef = useCallback(
    (n: HTMLDivElement) => {
      el.current = n;
      dropRef(n);
    },
    [dropRef]
  );
  return (
    <div ref={innerRef}>
      {!!focusedFolderNodeList?.length && (
        <div className={folderViewSection}>
          {focusedFolderNodeList?.map((node, index) => (
            <Folder key={index} node={node} />
          ))}
        </div>
      )}
      {!!focusedLinkNodeList?.length && (
        <div className={linkViewSection}>
          {focusedLinkNodeList?.map((node, index) => (
            <Pick key={index} node={node} />
          ))}
        </div>
      )}
    </div>
  );
};
