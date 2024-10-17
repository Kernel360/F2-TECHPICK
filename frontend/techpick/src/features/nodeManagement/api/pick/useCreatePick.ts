import { useMutation } from '@tanstack/react-query';
import { ApiPickData, ApiPickRequestType } from '@/shared/types/ApiTypes';
import { postPick } from '@/features/nodeManagement/api/pick/pickQueryFunctions';

export const useCreatePick = () => {
  return useMutation<ApiPickData, Error, ApiPickRequestType>({
    mutationFn: postPick,
  });
};
