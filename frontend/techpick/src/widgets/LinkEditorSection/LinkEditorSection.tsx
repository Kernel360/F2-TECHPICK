import React from 'react';
import {
  linkViewSection,
  folderViewSection,
  linkEditorLabel,
  linkEditorSectionFooter,
  linkEditorHeader,
  linkEditorSection,
  linkEditor,
  searchSection,
  folderWrapper,
  linkWrapper,
} from './linkEditorSection.css';
import Image from 'next/image';
import { NodeApi } from 'react-arborist';
import { NodeData } from '@/shared/types/NodeData';

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
        {focusedNode && (
          <div className={folderViewSection}>
            {focusedNodeFolderData?.map((node, index) => (
              <div key={index} className={folderWrapper}>
                <Image
                  src={`image/ic_folder.svg`}
                  alt="folder"
                  width={64}
                  height={64}
                />
                {node.name}
              </div>
            ))}
          </div>
        )}
        {focusedNode && (
          <div className={linkViewSection}>
            {focusedNodeLinkData?.map((node, index) => (
              <div key={index} className={linkWrapper}>
                <Image
                  src={`image/ic_doc.svg`}
                  alt="folder"
                  width={64}
                  height={64}
                />
                {node.name}
              </div>
            ))}
          </div>
        )}
      </div>
      <div className={linkEditorSectionFooter}></div>
    </div>
  );
}
