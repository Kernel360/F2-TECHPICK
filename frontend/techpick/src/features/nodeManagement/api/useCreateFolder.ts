import { postFolder } from '@/features/nodeManagement/api/queryFunctions';
import { useMutation } from '@tanstack/react-query';
import { ApiFolderData } from '@/shared/types/ApiTypes';

export const useCreateFolder = () => {
  return useMutation<ApiFolderData, Error, string>({
    mutationFn: postFolder,
  });
};
