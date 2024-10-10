import React, { useRef } from 'react';
import {
  directoryLabel,
  directoryLabelContainer,
  directoryTree,
  directoryTreeContainer,
  directoryTreeSectionFooter,
  directoryTreeWrapper,
  leftSidebarSection,
  logo,
  profileContainer,
  profileSection,
} from './directoryTreeSection.css';
import Image from 'next/image';
import { ToggleThemeButton } from '@/features/';
import { NodeData } from '@/shared/types/NodeData';
import {
  CreateHandler,
  MoveHandler,
  NodeApi,
  Tree,
  TreeApi,
} from 'react-arborist';
import useResizeObserver from 'use-resize-observer';
import { DirectoryNode } from '@/components';
import { useDragDropManager } from 'react-dnd';
import { moveNode } from '@/features/moveNode';
import { useTreeStore } from '@/shared/stores/treeStore';
import { Folder } from 'lucide-react';
import { EditorContextMenu } from '../EditorContextMenu';
import { useMutation, useQuery } from '@tanstack/react-query';
import {
  getFoldersIdData,
  getStructure,
  postFolder,
  putFolderMove,
} from '@/features/queryFunctions';
import { createNode } from '@/features/createNode';
import { DefaultFolderIdData } from '@/shared/types/ApiTypes';

export function DirectoryTreeSection() {
  const { ref, width, height } = useResizeObserver<HTMLDivElement>();
  const treeRef = useRef<TreeApi<NodeData> | undefined>(undefined);
  const dragDropManager = useDragDropManager();
  const {
    focusedNode,
    focusedFolderNodeList,
    focusedLinkNodeList,
    setTreeRef,
    setFocusedFolderNodeList,
    setFocusedLinkNodeList,
    setFocusedNode,
  } = useTreeStore();

  const handleTreeRef = (instance: TreeApi<NodeData> | null | undefined) => {
    if (instance && !treeRef.current) {
      treeRef.current = instance;
      setTreeRef(treeRef);
    }
  };

  // ==============  기존 구조 데이터 가져오기  =============
  const {
    data: structureData,
    error: structureError,
    isLoading: isStructureLoading,
    refetch: refetchStructure,
  } = useQuery({
    queryKey: ['getStructure'],
    queryFn: getStructure,
  });

  // ============== 기본 폴더 id 가져오기 ============
  const { data: defaultFolderIdData } = useQuery<DefaultFolderIdData>({
    queryKey: ['getDefaultFolderId'],
    queryFn: getFoldersIdData,
  });

  // ==============  새로운 폴더 생성  =============
  const { mutateAsync: createFolder } = useMutation<
    {
      id: number;
      name: string;
      parentFolderId: number;
      userId: number;
    },
    Error,
    string
  >({
    mutationFn: postFolder,
  });

  // ============  폴더 이동 ================
  const { mutateAsync: moveFolder } = useMutation<
    void,
    Error,
    { folderId: string; structure: object }
  >({
    mutationFn: putFolderMove,
  });

  // ============== Tree 핸들러 함수 ===============
  const handleCreate: CreateHandler<NodeData> = async ({
    parentId,
    parentNode,
    index,
    type,
  }): Promise<{ id: string }> => {
    try {
      // 폴더 생성 (서버)
      const data = await createFolder('New Folder27');
      console.log('Server: Folder created:', data);

      // 폴더 생성 (클라이언트)
      const updatedTreeData = createNode(
        structureData!.root,
        focusedNode,
        type,
        treeRef.current,
        parentId,
        parentNode,
        index,
        data.id,
        data.name
      );

      // 서버에 업데이트된 트리 전송
      const serverData = {
        parentFolderId: defaultFolderIdData!.ROOT,
        structure: {
          root: updatedTreeData,
          recycleBin: [],
        },
      };
      console.log('defaultFolderIdData', defaultFolderIdData);
      console.log('서버용 data', serverData);
      // 폴더 이동 (서버)
      await moveFolder({ folderId: data.id.toString(), structure: serverData });

      // 구조 데이터 새로 가져오기
      refetchStructure();
    } catch (error) {
      console.error('Error creating folder:', error);
    }
    return { id: '999' };
  };

  const handleMove: MoveHandler<NodeData> = async ({
    dragIds,
    dragNodes,
    parentId,
    parentNode,
    index,
  }) => {
    const updatedTreeData = moveNode(
      structureData!.root,
      focusedNode,
      focusedFolderNodeList,
      focusedLinkNodeList,
      setFocusedFolderNodeList,
      setFocusedLinkNodeList,
      dragIds[0],
      dragNodes[0],
      parentId,
      parentNode,
      index
    );
    // 서버에 업데이트된 트리 전송
    const serverData = {
      parentFolderId: parentNode
        ? parentNode.data.folderId
        : defaultFolderIdData!.ROOT,
      structure: {
        root: updatedTreeData,
        recycleBin: [],
      },
    };
    console.log('defaultFolderIdData', defaultFolderIdData);
    console.log('서버에 보낼 데이터', serverData);

    await moveFolder({
      folderId: dragNodes[0].data.folderId!.toString(),
      structure: serverData,
    });
    refetchStructure();
  };

  return (
    <div className={leftSidebarSection}>
      <div className={profileSection}>
        <div className={profileContainer}>
          <Image
            src={`image/ic_user.svg`}
            width={32}
            height={32}
            alt="profile"
          />
          <div className={logo}>TechPick</div>
        </div>
        <ToggleThemeButton />
      </div>
      <div className={directoryTreeContainer}>
        <div className={directoryLabelContainer}>
          <Folder size={20} strokeWidth={1} />
          <div className={directoryLabel}>Directory</div>
          <button
            onClick={() => {
              treeRef.current?.createInternal();
            }}
          >
            +Folder
          </button>
          <button
            onClick={() => {
              treeRef.current?.createLeaf();
            }}
          >
            +Pick
          </button>
        </div>
        <div className={directoryTreeWrapper} ref={ref}>
          {isStructureLoading && <div>Loading...</div>}
          {structureError && <div>Error: {structureError.message}</div>}
          {!isStructureLoading && !structureError && (
            <EditorContextMenu>
              <Tree
                ref={handleTreeRef}
                className={directoryTree}
                data={structureData!.root}
                disableMultiSelection={true}
                onFocus={(node: NodeApi) => {
                  setFocusedNode(node);
                }}
                onMove={handleMove}
                onCreate={handleCreate}
                openByDefault={false}
                width={width}
                height={height && height - 8}
                rowHeight={32}
                indent={24}
                overscanCount={1}
                dndManager={dragDropManager}
              >
                {DirectoryNode}
              </Tree>
            </EditorContextMenu>
          )}
        </div>
      </div>
      <div className={directoryTreeSectionFooter}></div>
    </div>
  );
}
