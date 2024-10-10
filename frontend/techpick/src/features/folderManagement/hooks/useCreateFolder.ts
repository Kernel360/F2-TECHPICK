import { postFolder } from '@/features/queryFunctions';
import { useMutation } from '@tanstack/react-query';

export const useCreateFolder = () => {
  return useMutation<
    {
      id: number;
      name: string;
      parentFolderId: number;
      userId: number;
    },
    Error,
    string
  >({
    mutationFn: postFolder,
  });
};
