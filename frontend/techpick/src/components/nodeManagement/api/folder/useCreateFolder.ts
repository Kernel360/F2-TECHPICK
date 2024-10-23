import { useMutation } from '@tanstack/react-query';
import { ApiFolderData } from '@/types/ApiTypes';
import { postFolder } from '@/components/nodeManagement/api/folder/folderQueryFunctions';

export const useCreateFolder = () => {
  return useMutation<ApiFolderData, Error, string>({
    mutationFn: postFolder,
  });
};
