import { DirNodeProps } from '@/lib/types/NodeData';
import {
  dirIcFolder,
  dirNode,
  dirNodeWrapper,
} from '@/widgets/DirView/dirview.css';
import IcDownArrow from '@/lib/icons/ic_arrow_down.svg';
import IcRightArrow from '@/lib/icons/ic_arrow_right.svg';
import IcFolder from '@/lib/icons/ic_folder3.svg';
import IcDocument from '@/lib/icons/ic_doc3.svg';
import { FC } from 'react';
import toast from 'react-hot-toast';
import Image from 'next/image';

export const DirNode: FC<DirNodeProps> = ({ node, style, dragHandle }) => {
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
