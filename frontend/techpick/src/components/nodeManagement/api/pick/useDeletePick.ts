import { useMutation } from '@tanstack/react-query';
import { deletePick } from '@/components/nodeManagement/api/pick/pickQueryFunctions';

export const useDeletePick = () => {
  return useMutation({
    mutationFn: deletePick,
  });
};
