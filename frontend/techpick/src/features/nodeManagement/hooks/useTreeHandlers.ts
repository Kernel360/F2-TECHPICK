import { NodeData } from '@/shared/types';
import { createNode } from '@/features/createNode';
import { moveNode } from '@/features/moveNode';
import { useGetRootAndRecycleBinData } from '@/features/nodeManagement/hooks/useGetRootAndRecycleBinData';
import { useTreeStore } from '@/shared/stores/treeStore';
import { CreateHandler, MoveHandler, NodeApi } from 'react-arborist';
import { useMoveFolder } from '@/features/nodeManagement/hooks/useMoveFolder';
import { useGetDefaultFolderData } from '@/features/nodeManagement/hooks/useGetDefaultFolderData';
import { useRenameFolder } from '@/features/nodeManagement/hooks/useRenameFolder';
import { removeByIdFromStructure } from '@/features/nodeManagement/utils/removeByIdFromStructure';
import { getNewIdFromStructure } from '@/features/nodeManagement/utils/getNewIdFromStructure';
import { useQueryClient } from '@tanstack/react-query';
import { StructureData } from '@/shared/types/ApiTypes';

export const useTreeHandlers = () => {
  const { data: structureData, refetch: refetchStructure } =
    useGetRootAndRecycleBinData();
  const {
    treeRef,
    focusedNode,
    focusedFolderNodeList,
    focusedLinkNodeList,
    setFocusedLinkNodeList,
    setFocusedFolderNodeList,
  } = useTreeStore();
  const { data: defaultFolderIdData } = useGetDefaultFolderData();
  // const { mutateAsync: createFolder } = useCreateFolder();
  const { mutateAsync: moveFolder } = useMoveFolder();
  const { mutateAsync: renameFolder } = useRenameFolder();
  const queryClient = useQueryClient();
  // const cashedStructureData = queryClient.getQueryData([
  //   'rootAndRecycleBinData',
  // ]);

  // const userId = defaultFolderIdData?.userId;
  // const rootFolderId = defaultFolderIdData?.ROOT;
  const recycleBinId = defaultFolderIdData?.RECYCLE_BIN;
  // const unclassifiedId = defaultFolderIdData?.UNCLASSIFIED;

  const handleCreate: CreateHandler<NodeData> = async ({
    parentId,
    parentNode,
    index,
    type,
  }): Promise<{ id: string }> => {
    console.log('parentId', parentId);
    console.log('parentNode', parentNode);
    console.log('index', index);
    console.log('type', type);
    const newLocalNodeId = getNewIdFromStructure(
      structuredClone(structureData!.root)
    );

    // 폴더 생성 (클라이언트)
    const updatedTreeData = createNode(
      structuredClone(structureData!.root),
      focusedNode,
      type,
      treeRef.current!,
      parentId,
      parentNode,
      index,
      -1, // 가상 id, 서버에서 생성된 id로 대체됨
      'New Folder' // 가상 이름, 입력받은 이름으로 대체됨
    );

    queryClient.setQueryData(
      ['rootAndRecycleBinData'],
      (oldData: StructureData) => ({
        root: updatedTreeData,
        recycleBin: oldData.recycleBin,
      })
    );

    return { id: newLocalNodeId.toString() };
  };

  const handleDrag: MoveHandler<NodeData> = async ({
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
        recycleBin: structureData!.recycleBin,
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

  const handleRename = async ({
    name,
    node,
  }: {
    id: string;
    name: string;
    node: NodeApi;
  }) => {
    const realNodeId =
      node.data.type === 'folder' ? node.data.folderId : node.data.pickId;
    await renameFolder({ folderId: realNodeId.toString(), name });
    refetchStructure();
  };

  const handleMoveToTrash = async ({
    ids,
    nodes,
  }: {
    ids: string[];
    nodes: NodeApi[];
  }) => {
    const realNodeId =
      nodes[0].data.type === 'folder'
        ? nodes[0].data.folderId
        : nodes[0].data.pickId;

    const updatedRecycleBin = [...structureData!.recycleBin];
    updatedRecycleBin.push(nodes[0].data);

    const updatedTreeStructure = [...structureData!.root];
    removeByIdFromStructure(updatedTreeStructure, ids[0]);

    const serverData = {
      parentFolderId: recycleBinId,
      structure: {
        root: updatedTreeStructure,
        recycleBin: updatedRecycleBin,
      },
    };
    await moveFolder({
      folderId: realNodeId.toString(),
      structure: serverData,
    });
    refetchStructure();
  };

  return {
    handleCreate,
    handleDrag,
    handleRename,
    handleDelete: handleMoveToTrash,
  };
};
