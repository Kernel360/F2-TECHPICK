import { useMutation } from '@tanstack/react-query';
import { postPick } from '@/components/nodeManagement/api/pick/pickQueryFunctions';
import { ApiPickData, ApiPickRequestType } from '@/types/ApiTypes';

export const useCreatePick = () => {
  return useMutation<ApiPickData, Error, ApiPickRequestType>({
    mutationFn: postPick,
  });
};
