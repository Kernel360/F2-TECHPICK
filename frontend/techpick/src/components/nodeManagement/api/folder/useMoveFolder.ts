import { useMutation } from '@tanstack/react-query';
import { putFolderMove } from '@/components/nodeManagement/api/folder/folderQueryFunctions';

export const useMoveFolder = () => {
  return useMutation({
    mutationFn: putFolderMove,
  });
};
