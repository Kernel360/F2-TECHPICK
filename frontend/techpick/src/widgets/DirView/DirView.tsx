import React, { CSSProperties, FC } from 'react';
import {
  leftSection,
  middleSection,
  rightSection,
  leftHeader,
  viewContainer,
  viewWrapper,
  leftFooter,
  dirContainer,
  middleHeader,
  bookmarkContainer,
  middleFooter,
  locationContainer,
  topButtonContainer,
  dirNodeWrapper,
  dirRow,
  dirTree,
  dirNode,
  dirIcFolder,
  logo,
  logoContainer,
} from '@/widgets/DirView/dirview.css';
import ToggleTheme from '@/components/ToggleTheme/ToggleTheme';
import { NodeApi, Tree } from 'react-arborist';
import toast from 'react-hot-toast';
import IcFolder from '@/lib/icons/ic_folder.svg';
import IcRightArrow from '@/lib/icons/ic_arrow_right.svg';
import IcDownArrow from '@/lib/icons/ic_arrow_down.svg';
import IcSearch from '@/lib/icons/ic_search.svg';
import IcSort from '@/lib/icons/ic_sort.svg';
import IcProfile from '@/lib/icons/ic_profile.svg';
import IcDocument from '@/lib/icons/ic_document.svg';
import Image from 'next/image';

interface NodeData {
  id: string;
  name: string;
  type: 'folder' | 'link';
  children: NodeData[] | null;
}

interface DirNodeProps {
  node: NodeApi<NodeData>;
  style: CSSProperties;
  dragHandle?: (el: HTMLDivElement | null) => void;
}

const data: NodeData[] = [
  {
    id: '1',
    name: 'Favorites',
    type: 'folder',
    children: null, // 명시적으로 null 처리
  },
  {
    id: '2',
    name: 'Frontend',
    type: 'folder',
    children: [
      {
        id: 'b1',
        name: 'React',
        type: 'folder',
        children: [
          { id: 'b1a', name: 'React Hooks', type: 'link', children: null },
        ],
      },
      { id: 'b2', name: 'TypeScript', type: 'folder', children: null },
      { id: 'b3', name: 'CSS', type: 'folder', children: null },
    ],
  },
  {
    id: '3',
    name: 'Backend',
    type: 'folder',
    children: [
      { id: 'c1', name: 'Node.js', type: 'folder', children: null },
      { id: 'c2', name: 'Databases', type: 'folder', children: null },
      { id: 'c3', name: 'Docker', type: 'folder', children: null },
    ],
  },
  {
    id: '4',
    name: 'Full Stack',
    type: 'folder',
    children: [
      { id: 'd1', name: 'React', type: 'folder', children: null },
      { id: 'd2', name: 'Node.js', type: 'folder', children: null },
      { id: 'd3', name: 'Databases', type: 'folder', children: null },
    ],
  },
];

const DirNode: FC<DirNodeProps> = ({ node, style, dragHandle }) => {
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

// function DirRow({ node, innerRef, attrs, children }) {
//   return <div>{node.data.name}</div>;
// }

// function DirDragPreview({ offset, mouse, id, dragIds, isDragging }) {
//   return <div></div>;
// }

// function DirCursor({ top, left, indent }) {
//   return <div></div>;
// }

const DirView = () => {
  return (
    <div className={viewWrapper}>
      <div className={viewContainer}>
        <div className={leftSection}>
          <div className={leftHeader}>
            <div className={logoContainer}>
              <Image src={IcProfile} width={30} alt="profile" />
              <div className={logo}>TechPick</div>
            </div>
            <ToggleTheme />
          </div>
          <div className={dirContainer}>
            <Tree
              className={dirTree}
              rowClassName={dirRow}
              initialData={data}
              // renderRow={DirRow}
              // renderDragPreview={DirDragPreview}
              // renderCursor={DirCursor}
              openByDefault={false}
              width="100%"
              rowHeight={32}
              indent={24}
              overscanCount={1}
            >
              {DirNode}
            </Tree>
          </div>
          <div className={leftFooter}></div>
        </div>
        <div className={middleSection}>
          <div className={middleHeader}>
            <div className={locationContainer}>
              <div>React</div>
              <div>&gt;</div>
              <div>TypeScript</div>
            </div>

            <div className={topButtonContainer}>
              <div>
                <Image src={IcSort} alt="search" width={24} />
              </div>
              <div>
                <Image src={IcSearch} alt="search" width={24} />
              </div>
            </div>
          </div>
          <div className={bookmarkContainer}></div>
          <div className={middleFooter}></div>
        </div>
        <div className={rightSection}></div>
      </div>
    </div>
  );
};

export default DirView;
