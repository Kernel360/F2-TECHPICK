import { useMutation } from '@tanstack/react-query';
import { putFolderRename } from '@/features/queryFunctions';

export const useRenameFolder = () => {
  return useMutation({
    mutationFn: putFolderRename,
  });
};
