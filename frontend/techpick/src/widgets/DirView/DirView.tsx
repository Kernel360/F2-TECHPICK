import React from 'react';
import {
  leftSection,
  middleSection,
  rightSection,
  viewContainer,
  viewWrapper,
} from '@/widgets/DirView/dirview.css';

const DirView = () => {
  return (
    <div className={viewWrapper}>
      <div className={viewContainer}>
        <div className={leftSection}></div>
        <div className={middleSection}></div>
        <div className={rightSection}></div>
      </div>
    </div>
  );
};

export default DirView;
