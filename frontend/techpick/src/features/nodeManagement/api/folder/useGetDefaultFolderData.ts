import { ApiDefaultFolderIdData } from '@/shared/types/ApiTypes';
import { useQuery } from '@tanstack/react-query';
import { getFoldersIdData } from '@/features/nodeManagement/api/folder/folderQueryFunctions';

export const useGetDefaultFolderData = () => {
  return useQuery<ApiDefaultFolderIdData>({
    queryKey: ['getDefaultFolderId'],
    queryFn: getFoldersIdData,
  });
};
