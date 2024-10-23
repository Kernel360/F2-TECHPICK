import { useMutation } from '@tanstack/react-query';
import { putFolderRename } from '@/components/nodeManagement/api/folder/folderQueryFunctions';

export const useRenameFolder = () => {
  return useMutation({
    mutationFn: putFolderRename,
  });
};
