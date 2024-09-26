import { DirectoryNodeProps } from '@/shared/types/NodeData';
import {
  dirIcFolder,
  dirNode,
  dirNodeWrapper,
} from '@/widgets/MainView/MainView.css';
import Image from 'next/image';
import {
  IcDownArrow,
  IcRightArrow,
  IcFolder,
  IcDocument,
} from '../../shared/icons';
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
          <Image src={IcDownArrow} width={8} alt="downArrow" />
        ) : node.isLeaf ? (
          <div style={{ marginLeft: '8px' }}></div>
        ) : (
          <Image src={IcRightArrow} width={8} alt="rightArrow" />
        )}

        {node.data.type === 'folder' ? (
          <Image src={IcFolder} alt="Folder" className={dirIcFolder} />
        ) : (
          <Image src={IcDocument} alt="Document" className={dirIcFolder} />
        )}
        {node.data.name}
      </div>
    </div>
  );
};
