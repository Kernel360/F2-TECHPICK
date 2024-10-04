import { DirectoryNodeProps } from '@/shared/types/NodeData';
import { dirIcFolder, dirNode, dirNodeWrapper } from './directoryNode.css';
import Image from 'next/image';
import toast from 'react-hot-toast';

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
          <Image
            src={`image/ic_arrow_down.svg`}
            width={8}
            height={8}
            alt="downArrow"
          />
        ) : node.isLeaf ? (
          <div style={{ marginLeft: '8px' }}></div>
        ) : (
          <Image
            src={`image/ic_arrow_right.svg`}
            width={8}
            height={8}
            alt="rightArrow"
          />
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
