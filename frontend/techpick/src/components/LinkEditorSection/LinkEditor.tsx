import React, { useCallback, useRef } from 'react';
import { Folder, PickCard, PickCardGridLayout, TagPicker } from '@/components';
import { useDropHook } from '@/components/nodeManagement/hooks/useDropHook';
import { useTreeStore } from '@/stores/treeStore';
import { folderViewSection } from './LinkEditorSection.css';

export const LinkEditor = () => {
  const { treeRef, focusedNode, focusedFolderNodeList, focusedLinkNodeList } =
    useTreeStore();
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
      {/*{!!focusedLinkNodeList?.length && (*/}
      {/*  <div className={linkViewSection}>*/}
      {/*    {focusedLinkNodeList?.map((node, index) => (*/}
      {/*      <Pick key={index} node={node} />*/}
      {/*    ))}*/}
      {/*  </div>*/}
      {/*)}*/}
      {!!focusedLinkNodeList?.length && (
        <PickCardGridLayout>
          {focusedLinkNodeList.map((node, index) => {
            console.log(node.data);
            return (
              <PickCard key={index} pickId={Number(node)} node={node}>
                <TagPicker pickId={Number(node.data.pickId)} />
              </PickCard>
            );
          })}
        </PickCardGridLayout>
      )}
    </div>
  );
};
