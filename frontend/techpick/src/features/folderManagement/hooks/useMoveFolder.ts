import { putFolderMove } from '@/features/queryFunctions';
import { useMutation } from '@tanstack/react-query';

export const useMoveFolder = () => {
  return useMutation({
    mutationFn: putFolderMove,
  });
};
