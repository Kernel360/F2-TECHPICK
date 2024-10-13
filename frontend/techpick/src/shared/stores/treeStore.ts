import { create } from 'zustand';
import { NodeData } from '@/shared/types';
import { NodeApi, TreeApi } from 'react-arborist';
import React from 'react';

interface TreeState {
  treeData: NodeData[];
  treeRef: React.RefObject<TreeApi<NodeData> | undefined>;
  newNodeName: string;
  focusedNode: NodeApi | null;
  prevFocusedNode: NodeApi | null;
  focusedFolderNodeList: NodeApi[];
  focusedLinkNodeList: NodeApi[];

  setTreeData: (data: NodeData[]) => void;
  setTreeRef: (ref: React.RefObject<TreeApi<NodeData> | undefined>) => void;
  setNewNodeName: (name: string) => void;
  setFocusedNode: (node: NodeApi | null) => void;
  setPrevFocusedNode: (node: NodeApi | null) => void;
  setFocusedFolderNodeList: (node: NodeApi[]) => void;
  setFocusedLinkNodeList: (node: NodeApi[]) => void;
}

export const useTreeStore = create<TreeState>((set) => ({
  treeData: [],
  treeRef: React.createRef(),
  newNodeName: '',
  focusedNode: null,
  prevFocusedNode: null,
  focusedFolderNodeList: [],
  focusedLinkNodeList: [],

  setTreeData: (data) => set({ treeData: data }),
  setTreeRef: (ref) => set({ treeRef: ref }),
  setNewNodeName: (name) => set({ newNodeName: name }),
  setFocusedNode: (node) => set({ focusedNode: node }),
  setPrevFocusedNode: (node) => set({ prevFocusedNode: node }),
  setFocusedFolderNodeList: (node) => set({ focusedFolderNodeList: node }),
  setFocusedLinkNodeList: (node) => set({ focusedLinkNodeList: node }),
}));
