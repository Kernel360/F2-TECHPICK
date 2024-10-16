import { create } from 'zustand';
import { NodeData } from '@/shared/types';
import { NodeApi, TreeApi } from 'react-arborist';
import React, { createRef } from 'react';

interface TreeState {
  treeData: NodeData[];
  treeRef: {
    rootRef: React.RefObject<TreeApi<NodeData> | undefined>;
    recycleBinRef: React.RefObject<TreeApi<NodeData> | undefined>;
  };
  focusedNode: NodeApi | null;
  focusedFolderNodeList: NodeApi[];
  focusedLinkNodeList: NodeApi[];

  setTreeData: (data: NodeData[]) => void;
  setTreeRef: (
    rootRef: React.RefObject<TreeApi<NodeData> | undefined>,
    recycleBinRef: React.RefObject<TreeApi<NodeData> | undefined>
  ) => void;
  setFocusedNode: (node: NodeApi | null) => void;
  setFocusedFolderNodeList: (node: NodeApi[]) => void;
  setFocusedLinkNodeList: (node: NodeApi[]) => void;
}

export const useTreeStore = create<TreeState>((set) => ({
  treeData: [],
  treeRef: {
    rootRef: createRef(),
    recycleBinRef: createRef(),
  },
  focusedNode: null,
  focusedFolderNodeList: [],
  focusedLinkNodeList: [],

  setTreeData: (data) => set({ treeData: data }),
  setTreeRef: (rootRef, recycleBinRef) =>
    set({ treeRef: { rootRef, recycleBinRef } }),
  setFocusedNode: (node) => set({ focusedNode: node }),
  setFocusedFolderNodeList: (node) => set({ focusedFolderNodeList: node }),
  setFocusedLinkNodeList: (node) => set({ focusedLinkNodeList: node }),
}));
