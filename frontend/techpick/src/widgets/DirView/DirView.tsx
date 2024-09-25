import React, { CSSProperties, FC, useMemo } from 'react';
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
  middleView,
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
  dirHeader,
  dirHeaderWrapper,
  folderContainer,
  bookmarkContainer,
  treeWrapper,
} from '@/widgets/DirView/dirview.css';
import ToggleTheme from '@/components/ToggleTheme/ToggleTheme';
import { NodeApi, Tree } from 'react-arborist';
import toast from 'react-hot-toast';
import IcFolder from '@/lib/icons/ic_folder3.svg';
import IcRightArrow from '@/lib/icons/ic_arrow_right.svg';
import IcDownArrow from '@/lib/icons/ic_arrow_down.svg';
import IcSearch from '@/lib/icons/ic_search.svg';
import IcSort from '@/lib/icons/ic_sort.svg';
import IcProfile from '@/lib/icons/ic_user2.svg';
import IcDocument from '@/lib/icons/ic_doc3.svg';
import IcDirectory from '@/lib/icons/ic_directory.svg';
import Image from 'next/image';
import useResizeObserver from 'use-resize-observer';

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

const mockData: NodeData[] = [
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
          { id: 'b1b', name: 'React Router', type: 'link', children: null },
          { id: 'b1c', name: 'React Context', type: 'link', children: null },
        ],
      },
      {
        id: 'b2',
        name: 'TypeScript',
        type: 'folder',
        children: [
          {
            id: 'b2a',
            name: 'TypeScript Basics',
            type: 'link',
            children: null,
          },
          { id: 'b2b', name: 'Advanced Types', type: 'link', children: null },
        ],
      },
      {
        id: 'b3',
        name: 'CSS',
        type: 'folder',
        children: [
          { id: 'b3a', name: 'Flexbox', type: 'link', children: null },
          { id: 'b3b', name: 'Grid Layout', type: 'link', children: null },
        ],
      },
    ],
  },
  {
    id: '3',
    name: 'Backend',
    type: 'folder',
    children: [
      {
        id: 'c1',
        name: 'Node.js',
        type: 'folder',
        children: [
          { id: 'c1a', name: 'Express.js', type: 'link', children: null },
          { id: 'c1a', name: 'Express.js', type: 'folder', children: null },
          { id: 'c1b', name: 'Nest.js', type: 'link', children: null },
        ],
      },
      {
        id: 'c2',
        name: 'Databases',
        type: 'folder',
        children: [
          { id: 'c2a', name: 'MongoDB', type: 'link', children: null },
          { id: 'c2b', name: 'PostgreSQL', type: 'link', children: null },
        ],
      },
      {
        id: 'c3',
        name: 'Docker',
        type: 'folder',
        children: [
          { id: 'c3a', name: 'Docker Basics', type: 'link', children: null },
          { id: 'c3b', name: 'Docker Compose', type: 'link', children: null },
        ],
      },
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
  const [data] = React.useState(mockData);
  const [focusedNode, setFocusedNode] =
    React.useState<NodeApi<NodeData> | null>(null);
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();

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
        <div className={leftSection}>
          <div className={leftHeader}>
            <div className={logoContainer}>
              <Image src={IcProfile} width={32} alt="profile" />
              <div className={logo}>TechPick</div>
            </div>
            <ToggleTheme />
          </div>
          <div className={dirContainer}>
            <div className={dirHeaderWrapper}>
              <Image src={IcDirectory} width={20} alt="directory" />
              <div className={dirHeader}>Directory</div>
            </div>
            <div className={treeWrapper} ref={ref}>
              <Tree
                className={dirTree}
                rowClassName={dirRow}
                initialData={data}
                onFocus={(node: NodeApi<NodeData>) => {
                  setFocusedNode(node);
                }}
                // renderRow={DirRow}
                // renderDragPreview={DirDragPreview}
                // renderCursor={DirCursor}
                openByDefault={false}
                width={width}
                height={height && height - 8}
                rowHeight={32}
                indent={24}
                overscanCount={1}
              >
                {DirNode}
              </Tree>
            </div>
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
          <div className={middleView}>
            <div className={folderContainer}>
              {focusedNode && (
                <Tree
                  className={dirTree}
                  rowClassName={dirRow}
                  data={focusedNodeFolderData}
                  // renderRow={DirRow}
                  // renderDragPreview={DirDragPreview}
                  // renderCursor={DirCursor}
                  openByDefault={false}
                  width={width}
                  height={height && height - 8}
                  rowHeight={32}
                  indent={24}
                  overscanCount={1}
                >
                  {DirNode}
                </Tree>
              )}
            </div>
            <div className={bookmarkContainer}>
              {focusedNode && (
                <Tree
                  className={dirTree}
                  rowClassName={dirRow}
                  data={focusedNodeLinkData}
                  // renderRow={DirRow}
                  // renderDragPreview={DirDragPreview}
                  // renderCursor={DirCursor}
                  openByDefault={false}
                  width={width}
                  height={height && height - 8}
                  rowHeight={32}
                  indent={24}
                  overscanCount={1}
                >
                  {DirNode}
                </Tree>
              )}
            </div>
          </div>
          <div className={middleFooter}></div>
        </div>
        <div className={rightSection}></div>
      </div>
    </div>
  );
};

export default DirView;
