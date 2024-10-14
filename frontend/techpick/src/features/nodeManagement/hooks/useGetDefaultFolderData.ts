import { DefaultFolderIdData } from '@/shared/types/ApiTypes';
import { getFoldersIdData } from '@/features/nodeManagement/api/queryFunctions';
import { useQuery } from '@tanstack/react-query';

export const useGetDefaultFolderData = () => {
  return useQuery<DefaultFolderIdData>({
    queryKey: ['getDefaultFolderId'],
    queryFn: getFoldersIdData,
  });
};
