import { useMutation } from '@tanstack/react-query';
import { putFolderMove } from '@/features/nodeManagement/api/folder/folderQueryFunctions';

export const useMoveFolder = () => {
  return useMutation({
    mutationFn: putFolderMove,
  });
};
