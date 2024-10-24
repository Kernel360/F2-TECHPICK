import { useQueryClient } from '@tanstack/react-query';
import { NodeApi } from 'react-arborist';
import { useGetDefaultFolderData } from '@/components/nodeManagement/api/folder/useGetDefaultFolderData';
import { useMoveFolder } from '@/components/nodeManagement/api/folder/useMoveFolder';
import { useMovePick } from '@/components/nodeManagement/api/pick/useMovePick';
import { removeByIdFromStructure } from '@/components/nodeManagement/utils/removeByIdFromStructure';
import { ApiStructureData } from '@/types/ApiTypes';

export const useRestoreNode = () => {
  const { mutateAsync: moveFolder } = useMoveFolder();
  const { mutateAsync: movePick } = useMovePick();

  const { data: defaultFolderIdData } = useGetDefaultFolderData();
  const queryClient = useQueryClient();
  const structureData: ApiStructureData | undefined = queryClient.getQueryData([
    'rootAndRecycleBinData',
  ]);

  const restoreNode = async ({
    ids,
    nodes,
  }: {
    ids: string[];
    nodes: NodeApi[];
  }) => {
    const isFolder = nodes[0].data.type === 'folder';
    const realNodeId = isFolder ? nodes[0].data.folderId : nodes[0].data.pickId;

    const updatedRoot = structuredClone(structureData!.root);
    updatedRoot.splice(0, 0, nodes[0].data);

    const updatedRecycleBin = removeByIdFromStructure(
      structuredClone(structureData!.recycleBin),
      ids[0]
    );

    const serverData = {
      parentFolderId: defaultFolderIdData!.ROOT,
      structure: {
        root: updatedRoot,
        recycleBin: updatedRecycleBin,
      },
    };

    if (isFolder) {
      await moveFolder({
        folderId: realNodeId.toString(),
        structure: serverData,
      });
    } else {
      await movePick({
        pickId: realNodeId.toString(),
        structure: serverData,
      });
    }

    await queryClient.invalidateQueries({
      queryKey: ['rootAndRecycleBinData'],
      exact: true,
    });
  };

  return { restoreNode };
};
