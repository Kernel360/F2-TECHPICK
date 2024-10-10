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

  const handleCreate: CreateHandler<NodeData> = async ({
    parentId,
    parentNode,
    index,
    type,
  }): Promise<{ id: string }> => {
    try {
      // 폴더 생성 (서버)
      const data = await createFolder('New Folder29');
      console.log('Server: Folder created:', data);

      // 폴더 생성 (클라이언트)
      const updatedTreeData = createNode(
        structureData!.root,
        focusedNode,
        type,
        treeRef.current!,
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
    return { id: 'id' };
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

  const handleRename = async ({
    id,
    name,
    node,
  }: {
    id: string;
    name: string;
    node: NodeApi;
  }) => {
    console.log(id, name, node);
    const realNodeId =
      node.data.type === 'folder' ? node.data.folderId : node.data.pickId;
    await renameFolder({ folderId: realNodeId.toString(), name });
    refetchStructure();
  };

  return { handleCreate, handleMove, handleRename };
};
