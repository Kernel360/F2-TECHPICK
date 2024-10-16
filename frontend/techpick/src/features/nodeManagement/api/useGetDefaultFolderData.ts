import { ApiDefaultFolderIdData } from '@/shared/types/ApiTypes';
import { getFoldersIdData } from '@/features/nodeManagement/api/queryFunctions';
import { useQuery } from '@tanstack/react-query';

export const useGetDefaultFolderData = () => {
  return useQuery<ApiDefaultFolderIdData>({
    queryKey: ['getDefaultFolderId'],
    queryFn: getFoldersIdData,
  });
};
