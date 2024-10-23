'use client';

import { PropsWithChildren } from 'react';
import { pickCardGridLayoutStyle } from './pickCardGridLayout.css';

export function PickCardGridLayout({ children }: PropsWithChildren) {
  return <div className={pickCardGridLayoutStyle}>{children}</div>;
}
