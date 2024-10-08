import { create } from 'zustand';
import { NodeData } from '@/shared/types';
import { dynamicMockData } from '@/shared/const/mockdata';
import { NodeApi, TreeApi } from 'react-arborist';
import React from 'react';

interface TreeState {
  treeData: NodeData[];
  treeApi: TreeApi<NodeApi> | null;
  treeRef: React.RefObject<TreeApi<NodeData> | undefined>;
  focusedNode: NodeApi | null;
  prevFocusedNode: NodeApi | null;
  focusedFolderNodeList: NodeApi[];
  focusedLinkNodeList: NodeApi[];
  createNodeType: 'folder' | 'pick';

  setTreeData: (data: NodeData[]) => void;
  setTreeApi: (api: TreeApi<NodeApi>) => void;
  setTreeRef: (ref: React.RefObject<TreeApi<NodeData> | undefined>) => void;
  setFocusedNode: (node: NodeApi | null) => void;
  setPrevFocusedNode: (node: NodeApi | null) => void;
  setFocusedFolderNodeList: (node: NodeApi[]) => void;
  setFocusedLinkNodeList: (node: NodeApi[]) => void;
  setCreateNodeType: (type: 'folder' | 'pick') => void;
}

export const useTreeStore = create<TreeState>((set) => ({
  treeData: dynamicMockData,
  treeApi: null,
  treeRef: React.createRef(),
  focusedNode: null,
  prevFocusedNode: null,
  focusedFolderNodeList: [],
  focusedLinkNodeList: [],
  createNodeType: 'pick',

  setTreeData: (data) => set({ treeData: data }),
  setTreeApi: (api) => set({ treeApi: api }),
  setTreeRef: (ref) => set({ treeRef: ref }),
  setFocusedNode: (node) => set({ focusedNode: node }),
  setPrevFocusedNode: (node) => set({ prevFocusedNode: node }),
  setFocusedFolderNodeList: (node) => set({ focusedFolderNodeList: node }),
  setFocusedLinkNodeList: (node) => set({ focusedLinkNodeList: node }),
  setCreateNodeType: (type) => set({ createNodeType: type }),
}));
