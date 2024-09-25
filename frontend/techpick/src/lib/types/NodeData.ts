import { NodeApi } from 'react-arborist';
import { CSSProperties } from 'react';

export interface NodeData {
  id: string;
  name: string;
  type: 'folder' | 'link';
  children: NodeData[] | null;
}

export interface DirNodeProps {
  node: NodeApi<NodeData>;
  style: CSSProperties;
  dragHandle?: (el: HTMLDivElement | null) => void;
}
