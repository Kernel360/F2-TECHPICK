import { useMutation } from '@tanstack/react-query';
import { deleteFolder } from '@/components/nodeManagement/api/folder/folderQueryFunctions';

export const useDeleteFolder = () => {
  return useMutation({
    mutationFn: deleteFolder,
  });
};
