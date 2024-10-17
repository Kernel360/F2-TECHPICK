import { create } from 'zustand';
import { NodeData } from '@/shared/types';
import { NodeApi, TreeApi } from 'react-arborist';
import React, { createRef } from 'react';
import { ApiDefaultFolderIdData, ApiPickData } from '@/shared/types/ApiTypes';

interface TreeState {
  treeData: NodeData[];
  treeRef: {
    rootRef: React.RefObject<TreeApi<NodeData> | undefined>;
    recycleBinRef: React.RefObject<TreeApi<NodeData> | undefined>;
  };
  focusedNode: NodeApi | null;
  focusedNodeInEditorSection: NodeApi | null;
  focusedFolderNodeList: NodeApi[];
  focusedLinkNodeList: NodeApi[];
  unClassifiedPickDataList: ApiPickData[];
  unClassifiedNodeRoot: NodeApi | null;
  defaultFolderIdData: ApiDefaultFolderIdData | null;

  setDeFaultFolderIdData: (data: ApiDefaultFolderIdData) => void;
  setUnclassifiedNodeRoot: (data: NodeApi | null) => void;
  setUnClassifiedPickDataList: (data: ApiPickData[]) => void;
  setTreeData: (data: NodeData[]) => void;
  setTreeRef: (
    rootRef: React.RefObject<TreeApi<NodeData> | undefined>,
    recycleBinRef: React.RefObject<TreeApi<NodeData> | undefined>
  ) => void;
  setFocusedNode: (node: NodeApi | null) => void;
  setFocusedNodeInEditorSection: (node: NodeApi | null) => void;
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
  focusedNodeInEditorSection: null,
  focusedFolderNodeList: [],
  focusedLinkNodeList: [],
  unClassifiedPickDataList: [],
  unClassifiedNodeRoot: null,
  defaultFolderIdData: null,

  setDeFaultFolderIdData: (data) => set({ defaultFolderIdData: data }),
  setUnclassifiedNodeRoot: (data) => set({ unClassifiedNodeRoot: data }),
  setUnClassifiedPickDataList: (data) =>
    set({ unClassifiedPickDataList: data }),
  setTreeData: (data) => set({ treeData: data }),
  setTreeRef: (rootRef, recycleBinRef) =>
    set({ treeRef: { rootRef, recycleBinRef } }),
  setFocusedNode: (node) => set({ focusedNode: node }),
  setFocusedNodeInEditorSection: (node) =>
    set({ focusedNodeInEditorSection: node }),
  setFocusedFolderNodeList: (node) => set({ focusedFolderNodeList: node }),
  setFocusedLinkNodeList: (node) => set({ focusedLinkNodeList: node }),
}));
