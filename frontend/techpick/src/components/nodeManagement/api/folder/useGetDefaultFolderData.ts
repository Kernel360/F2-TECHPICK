import { useQuery } from '@tanstack/react-query';
import { getFoldersIdData } from '@/components/nodeManagement/api/folder/folderQueryFunctions';
import { ApiDefaultFolderIdData } from '@/types/ApiTypes';

export const useGetDefaultFolderData = () => {
  return useQuery<ApiDefaultFolderIdData>({
    queryKey: ['getDefaultFolderId'],
    queryFn: getFoldersIdData,
  });
};
