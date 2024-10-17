import { useMutation } from '@tanstack/react-query';
import { putFolderRename } from '@/features/nodeManagement/api/folder/folderQueryFunctions';

export const useRenameFolder = () => {
  return useMutation({
    mutationFn: putFolderRename,
  });
};
