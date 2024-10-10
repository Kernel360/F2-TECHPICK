import { DirectoryNodeProps } from '@/shared/types/NodeData';
import {
  dirIcFolder,
  dirNode,
  dirNodeWrapper,
  dirNodeWrapperFocused,
  nodeNameInput,
} from './directoryNode.css';
import Image from 'next/image';
import { ChevronRight, ChevronDown } from 'lucide-react';

export const DirectoryNode = ({
  node,
  style,
  dragHandle,
}: DirectoryNodeProps) => {
  const handleKeyDown = (event: React.KeyboardEvent<HTMLInputElement>) => {
    if (event.key === 'Enter') {
      node.submit(event.currentTarget.value);
    }
    if (event.key === 'Escape') {
      node.reset();
    }
  };

  return (
    <div
      style={{ ...style, outline: 'none' }}
      className={node.isSelected ? dirNodeWrapperFocused : dirNodeWrapper}
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
        {node.isEditing ? (
          <input
            type="text"
            className={nodeNameInput}
            onKeyDown={handleKeyDown}
            autoFocus
          />
        ) : (
          node.data.name
        )}
      </div>
    </div>
  );
};
