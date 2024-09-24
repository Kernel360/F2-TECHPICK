import React from 'react';
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
} from '@/widgets/DirView/dirview.css';
import ToggleTheme from '@/components/ToggleTheme/ToggleTheme';
import { Tree } from 'react-arborist';

const data = [
  {
    id: '1',
    name: 'Favorites',
  },
  {
    id: '2',
    name: 'Frontend',
    children: [
      {
        id: 'b1',
        name: 'React',
        children: [{ id: 'b1a', name: 'React Hooks' }],
      },

      { id: 'b2', name: 'TypeScript' },
      { id: 'b3', name: 'CSS' },
    ],
  },
  {
    id: '3',
    name: 'Backend',
    children: [
      { id: 'c1', name: 'Node.js' },
      { id: 'c2', name: 'Databases' },
      { id: 'c3', name: 'Docker' },
    ],
  },
  {
    id: '4',
    name: 'Full Stack',
    children: [
      { id: 'd1', name: 'React' },
      { id: 'd2', name: 'Node.js' },
      { id: 'd3', name: 'Databases' },
    ],
  },
];

const DirView = () => {
  return (
    <div className={viewWrapper}>
      <div className={viewContainer}>
        <div className={leftSection}>
          <div className={leftHeader}>
            <div>Profile</div>
            <div>TechPick</div>
            <ToggleTheme />
          </div>
          <div className={dirContainer}>
            <Tree
              initialData={data}
              openByDefault={false}
              width={300}
              height={400}
              indent={24}
              rowHeight={36}
              overscanCount={1}
              paddingTop={16}
              paddingBottom={16}
            ></Tree>
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
              <div>Sort</div>
              <div>Search</div>
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
