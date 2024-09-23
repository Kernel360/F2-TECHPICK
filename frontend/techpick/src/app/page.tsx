'use client';

// const a = 1;

import DirView from '@/widgets/DirView/DirView';
import { rootWrapper } from '@/app/style.css';

export default function Home() {
  return (
    <div className={rootWrapper}>
      <DirView />
    </div>
  );
}
