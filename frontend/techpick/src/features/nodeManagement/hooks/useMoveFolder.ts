import { putFolderMove } from '@/features/nodeManagement/api/queryFunctions';
import { useMutation } from '@tanstack/react-query';

export const useMoveFolder = () => {
  return useMutation({
    mutationFn: putFolderMove,
  });
};
