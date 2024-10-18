import { useMutation } from '@tanstack/react-query';
import { putPickMove } from '@/features/nodeManagement/api/pick/pickQueryFunctions';

export const useMovePick = () => {
  return useMutation({
    mutationFn: putPickMove,
  });
};
