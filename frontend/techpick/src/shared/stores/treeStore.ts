import { create } from 'zustand';
import { NodeData } from '@/shared/types';
import { dynamicMockData } from '@/shared/const/mockdata';

interface TreeState {
  treeData: NodeData[];
  setTreeData: (data: NodeData[]) => void;
}

export const useTreeStore = create<TreeState>((set) => ({
  treeData: dynamicMockData,
  setTreeData: (data) => set({ treeData: data }),
}));
