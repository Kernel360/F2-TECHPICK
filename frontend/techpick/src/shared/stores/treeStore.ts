import { create } from 'zustand';
import { NodeData } from '@/shared/types';
import { dynamicMockData } from '@/shared/const/mockdata';
import { NodeApi, TreeApi } from 'react-arborist';
import React from 'react';

interface TreeState {
  treeData: NodeData[];
  treeRef: React.RefObject<TreeApi<NodeData> | undefined>;
  focusedNode: NodeApi | null;
  prevFocusedNode: NodeApi | null;
  focusedFolderNodeList: NodeApi[];
  focusedLinkNodeList: NodeApi[];

  setTreeData: (data: NodeData[]) => void;
  setTreeRef: (ref: React.RefObject<TreeApi<NodeData> | undefined>) => void;
  setFocusedNode: (node: NodeApi | null) => void;
  setPrevFocusedNode: (node: NodeApi | null) => void;
  setFocusedFolderNodeList: (node: NodeApi[]) => void;
  setFocusedLinkNodeList: (node: NodeApi[]) => void;
}

export const useTreeStore = create<TreeState>((set) => ({
  treeData: dynamicMockData,
  treeRef: React.createRef(),
  focusedNode: null,
  prevFocusedNode: null,
  focusedFolderNodeList: [],
  focusedLinkNodeList: [],

  setTreeData: (data) => set({ treeData: data }),
  setTreeRef: (ref) => set({ treeRef: ref }),
  setFocusedNode: (node) => set({ focusedNode: node }),
  setPrevFocusedNode: (node) => set({ prevFocusedNode: node }),
  setFocusedFolderNodeList: (node) => set({ focusedFolderNodeList: node }),
  setFocusedLinkNodeList: (node) => set({ focusedLinkNodeList: node }),
}));
