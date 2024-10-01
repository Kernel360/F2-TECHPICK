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
  linkWrapper,
} from './linkEditorSection.css';
import Image from 'next/image';
import { NodeApi } from 'react-arborist';
import { Folder } from '@/features/Draggable/Folder';

interface LinkEditorSectionProps {
  focusedNode: NodeApi | undefined;
  focusedNodeFolder: NodeApi[] | undefined;
  focusedNodeLink: NodeApi[] | undefined;
}

export function LinkEditorSection({
  focusedNode,
  focusedNodeFolder,
  focusedNodeLink,
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
          <div>
            {!!focusedNodeFolder?.length && (
              <div className={folderViewSection}>
                {focusedNodeFolder?.map((node, index) => (
                  <Folder key={index} node={node} />
                ))}
              </div>
            )}
            {!!focusedNodeLink?.length && (
              <div className={linkViewSection}>
                {focusedNodeLink?.map((node, index) => (
                  <div key={index} className={linkWrapper}>
                    <Image
                      src={`image/ic_doc.svg`}
                      alt="folder"
                      width={64}
                      height={64}
                    />
                    {node.data.name}
                  </div>
                ))}
              </div>
            )}
          </div>
        )}
      </div>
      <div className={linkEditorSectionFooter}></div>
    </div>
  );
}
