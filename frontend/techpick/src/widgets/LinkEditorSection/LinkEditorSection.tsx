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
} from './linkEditorSection.css';
import { NodeApi } from 'react-arborist';
import { Folder } from '@/features/Draggable/Folder';
import { Pick } from '@/features/Draggable/Pick';
import { ArrowDownAZ, Search } from 'lucide-react';

interface LinkEditorSectionProps {
  focusedNode: NodeApi | null;
  focusedNodeFolder: NodeApi[] | undefined;
  focusedNodeLink: NodeApi[] | undefined;
}

export function LinkEditorSection({
  focusedNode,
  focusedNodeFolder,
  focusedNodeLink,
}: LinkEditorSectionProps) {
  function renderDirectoryName(node: NodeApi) {
    const nameList = [];

    nameList.push(node.data.name);

    let parent = node.parent;
    while (parent) {
      nameList.push(parent.data.name);
      parent = parent.parent;
    }

    return nameList.reverse().join(' / ');
  }

  return (
    <div className={linkEditorSection}>
      <div className={linkEditorHeader}>
        <div className={linkEditorLabel}>
          {focusedNode && renderDirectoryName(focusedNode)}
        </div>

        <div className={searchSection}>
          <div>
            <ArrowDownAZ size={24} />
          </div>
          <div>
            <Search size={24} />
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
                  <Pick key={index} node={node} />
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
