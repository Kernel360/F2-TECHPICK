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
          <div className={dirContainer}></div>
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
