import { deleteFolder } from '@/features/nodeManagement/api/queryFunctions';
import { useMutation } from '@tanstack/react-query';

export const useDeleteFolder = () => {
  return useMutation({
    mutationFn: deleteFolder,
  });
};
