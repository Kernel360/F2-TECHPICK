import { DirectoryNodeProps } from '@/shared/types/NodeData';
import { dirIcFolder, dirNode, dirNodeWrapper } from './directoryNode.css';
import Image from 'next/image';
import toast from 'react-hot-toast';
import { ChevronRight, ChevronDown } from 'lucide-react';

export const DirectoryNode = ({
  node,
  style,
  dragHandle,
}: DirectoryNodeProps) => {
  return (
    <div
      className={dirNodeWrapper}
      style={{ ...style }}
      ref={dragHandle}
      onClick={() => {
        toast(node.data.id);
        node.toggle();
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
            alt="Folder"
            className={dirIcFolder}
            width={8}
            height={8}
          />
        ) : (
          <Image
            src={`image/ic_doc.svg`}
            width={8}
            height={8}
            alt="Document"
            className={dirIcFolder}
          />
        )}
        {node.data.name}
      </div>
    </div>
  );
};
