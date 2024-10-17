import React, { useCallback, useRef } from 'react';

import { Folder } from '@/features/nodeManagement/ui/Folder';
import { Pick } from '@/features/nodeManagement/ui/Pick';
import { useTreeStore } from '@/shared/stores/treeStore';
import { useDropHook } from '@/features/nodeManagement/hooks/useDropHook';
import {
  folderViewSection,
  linkViewSection,
} from '@/widgets/LinkEditorSection/LinkEditorSection.css';
import { PickCard, PickCardGridLayout } from '@/entities/pick';
import { TagPicker } from '@/widgets/TagPicker';

export const LinkEditor = () => {
  const {
    treeRef,
    focusedNode,
    focusedFolderNodeList,
    focusedLinkNodeList,
    unClassifiedPicks,
  } = useTreeStore();
  const el = useRef<HTMLDivElement | null>(null);
  const dropRef = useDropHook(el, focusedNode || treeRef.rootRef.current!.root);

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
      {!!unClassifiedPicks?.length && !focusedNode && (
        <PickCardGridLayout>
          {unClassifiedPicks.map((node, index) => {
            return (
              <PickCard key={index} pickId={node.id}>
                <TagPicker />
              </PickCard>
            );
          })}
        </PickCardGridLayout>
      )}
    </div>
  );
};
