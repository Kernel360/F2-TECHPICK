'use client';

import { MainView } from '@/widgets';
import { rootLayout } from '@/app/style.css';

export default function MainPage() {
  return (
    <div className={rootLayout}>
      <MainView />
    </div>
  );
}
