import { useMutation, useQueryClient } from '@tanstack/react-query';
import { updatePick } from './updatePick';
import type { UpdatePickRequestType } from '../pickApi.type';

export const useUpdatePickMutation = (pickId: number) => {
  const queryClient = useQueryClient();

  return useMutation({
    mutationFn: (pickInfo: UpdatePickRequestType) => {
      return updatePick(pickInfo);
    },
    onSettled() {
      queryClient.invalidateQueries({
        queryKey: ['pick', pickId],
      });
    },
  });
};
