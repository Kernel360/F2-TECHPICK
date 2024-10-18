'use client';

import { DndProvider } from 'react-dnd';
import { HTML5Backend } from 'react-dnd-html5-backend';
import React from 'react';

export const DndProviderWrapper = ({
  children,
}: {
  children: React.ReactNode;
}) => {
  return <DndProvider backend={HTML5Backend}>{children}</DndProvider>;
};
