import React, { useMemo } from 'react';
import { viewContainer, viewWrapper } from '@/widgets/DirView/dirview.css';
import { NodeApi } from 'react-arborist';

import { NodeData } from '@/lib/types/NodeData';
import LeftSection from '@/widgets/LeftSection/LeftSection';
import RightSection from '@/widgets/RightSection/RightSection';
import MiddleSection from '@/widgets/MiddleSection/MiddleSection';

const DirView = () => {
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
    <div className={viewWrapper}>
      <div className={viewContainer}>
        <LeftSection setFocusedNode={setFocusedNode} />
        <MiddleSection
          focusedNode={focusedNode}
          focusedNodeFolderData={focusedNodeFolderData}
          focusedNodeLinkData={focusedNodeLinkData}
        />
        <RightSection />
      </div>
    </div>
  );
};

export default DirView;
