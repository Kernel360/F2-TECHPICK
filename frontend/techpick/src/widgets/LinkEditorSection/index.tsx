import React from 'react';
import {
  linkEditorLabel,
  linkEditorSectionFooter,
  linkEditorHeader,
  linkEditorSection,
  searchSection,
  linkEditor,
} from './LinkEditorSection.css';
import { NodeApi } from 'react-arborist';
import { ArrowDownAZ, Search } from 'lucide-react';
import { useTreeStore } from '@/shared/stores/treeStore';
import { LinkEditor } from '@/widgets/LinkEditorSection/LinkEditor';
import { ToggleThemeButton } from '@/features';

export function LinkEditorSection() {
  const { focusedNode, treeRef } = useTreeStore();

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
            <ToggleThemeButton />
          </div>
          <div>
            <ArrowDownAZ size={24} strokeWidth={1.3} />
          </div>
          <div>
            <Search size={24} strokeWidth={1.3} />
          </div>
        </div>
      </div>
      <div className={linkEditor}>
        {treeRef.rootRef.current && <LinkEditor />}
      </div>
      <div className={linkEditorSectionFooter}></div>
    </div>
  );
}
