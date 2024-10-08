import { DirectoryNodeProps } from '@/shared/types/NodeData';
import {
  dirIcFolder,
  dirNode,
  dirNodeWrapper,
  dirNodeWrapperFocused,
} from './directoryNode.css';
import Image from 'next/image';
import { ChevronRight, ChevronDown } from 'lucide-react';
import { useTreeStore } from '@/shared/stores/treeStore';

export const DirectoryNode = ({
  node,
  style,
  dragHandle,
}: DirectoryNodeProps) => {
  const { treeApi, setTreeApi } = useTreeStore();
  if (!treeApi) {
    setTreeApi(node.tree);
  }

  return (
    <div
      className={node.isSelected ? dirNodeWrapperFocused : dirNodeWrapper}
      style={{ ...style }}
      ref={dragHandle}
      onClick={() => {
        node.toggle();
        console.log('Clicked Node', node);
      }}
      onContextMenu={() => {
        node.select();
      }}
    >
      <div className={dirNode}>
        {node.isOpen ? (
          <ChevronDown size={13} />
        ) : node.isLeaf ? (
          <div style={{ marginLeft: '13px' }}></div>
        ) : (
          <ChevronRight size={13} />
        )}
        {node.data.type === 'folder' ? (
          <Image
            src={`image/ic_folder.svg`}
            alt={`${node.data.name}'s image`}
            className={dirIcFolder}
            width={8}
            height={8}
          />
        ) : (
          <Image
            src={`image/ic_doc.svg`}
            width={8}
            height={8}
            alt={`${node.data.name}'s image`}
            className={dirIcFolder}
          />
        )}
        {node.data.name}
      </div>
    </div>
  );
};
