import { NodeData } from '@/shared/types';
import { createNode } from '@/features/createNode';
import { moveNode } from '@/features/moveNode';
import { useGetStructure } from '@/features/folderManagement/hooks/useGetStructure';
import { useTreeStore } from '@/shared/stores/treeStore';
import { CreateHandler, MoveHandler, NodeApi } from 'react-arborist';
import { useCreateFolder } from '@/features/folderManagement/hooks/useCreateFolder';
import { useMoveFolder } from '@/features/folderManagement/hooks/useMoveFolder';
import { useGetDefaultFolderData } from '@/features/folderManagement/hooks/useGetDefaultFolderData';
import { useRenameFolder } from '@/features/folderManagement/hooks/useRenameFolder';

export const useTreeHandlers = () => {
  const { data: structureData, refetch: refetchStructure } = useGetStructure();
  const {
    treeRef,
    focusedNode,
    focusedFolderNodeList,
    focusedLinkNodeList,
    setFocusedLinkNodeList,
    setFocusedFolderNodeList,
  } = useTreeStore();
  const { data: defaultFolderIdData } = useGetDefaultFolderData();
  const { mutateAsync: createFolder } = useCreateFolder();
  const { mutateAsync: moveFolder } = useMoveFolder();
  const { mutateAsync: renameFolder } = useRenameFolder();

  // const userId = defaultFolderIdData?.userId;
  const rootFolderId = defaultFolderIdData?.ROOT;
  const recycleBinId = defaultFolderIdData?.RECYCLE_BIN;
  // const unclassifiedId = defaultFolderIdData?.UNCLASSIFIED;
  let newFolderId = '';

  const handleCreate: CreateHandler<NodeData> = async ({
    parentId,
    parentNode,
    index,
    type,
  }): Promise<{ id: string }> => {
    try {
      // 폴더 생성 (서버)
      const newFolderData = await createFolder('New Folder29');
      console.log('Server: Folder created:', newFolderData);
      newFolderId = newFolderData.id.toString();

      // 폴더 생성 (클라이언트)
      const updatedTreeData = createNode(
        structureData!.root,
        focusedNode,
        type,
        treeRef.current!,
        parentId,
        parentNode,
        index,
        newFolderData.id,
        newFolderData.name
      );

      // 서버에 업데이트된 트리 전송
      const serverData = {
        parentFolderId: rootFolderId,
        structure: {
          root: updatedTreeData,
          recycleBin: [],
        },
      };
      console.log('defaultFolderIdData', defaultFolderIdData);
      console.log('서버용 data', serverData);
      // 폴더 이동 (서버)
      await moveFolder({
        folderId: newFolderData.id.toString(),
        structure: serverData,
      });

      // 구조 데이터 새로 가져오기
      refetchStructure();
    } catch (error) {
      console.error('Error creating folder:', error);
    }
    return { id: newFolderId };
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

  const handleDelete = async ({
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
    removeNodeById(updatedTreeStructure, ids[0]);

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

  return { handleCreate, handleDrag, handleRename, handleDelete };
};

function removeNodeById(structure: NodeData[], targetId: string): NodeData[] {
  // 트리의 모든 노드를 순회하는 대신, id를 찾으면 즉시 반환
  for (let i = 0; i < structure.length; i++) {
    const node = structure[i];

    // 노드가 찾고자 하는 id와 일치하면 해당 노드를 제거
    if (node.id === targetId) {
      structure.splice(i, 1);
      return structure;
    }

    // children이 있으면 재귀적으로 탐색
    if (node.children && node.children.length > 0) {
      node.children = removeNodeById(node.children, targetId);
    }
  }

  return structure;
}
