import { useMutation } from '@tanstack/react-query';
import { ApiPickData, ApiPickRequestType } from '@/types/ApiTypes';
import { postPick } from '@/components/nodeManagement/api/pick/pickQueryFunctions';

export const useCreatePick = () => {
  return useMutation<ApiPickData, Error, ApiPickRequestType>({
    mutationFn: postPick,
  });
};
