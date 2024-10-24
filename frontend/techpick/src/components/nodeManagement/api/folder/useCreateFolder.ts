import { useMutation } from '@tanstack/react-query';
import { postFolder } from '@/components/nodeManagement/api/folder/folderQueryFunctions';
import { ApiFolderData } from '@/types/ApiTypes';

export const useCreateFolder = () => {
  return useMutation<ApiFolderData, Error, string>({
    mutationFn: postFolder,
  });
};
