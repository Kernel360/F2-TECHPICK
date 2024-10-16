import { useMutation } from '@tanstack/react-query';
import { putFolderRename } from '@/features/nodeManagement/api/queryFunctions';

export const useRenameFolder = () => {
  return useMutation({
    mutationFn: putFolderRename,
  });
};
