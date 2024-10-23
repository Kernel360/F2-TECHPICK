import { useQuery } from '@tanstack/react-query';
import { getRootAndRecycleBinData } from '@/components/nodeManagement/api/folder/folderQueryFunctions';

export const useGetRootAndRecycleBinData = () => {
  return useQuery({
    queryKey: ['rootAndRecycleBinData'],
    queryFn: getRootAndRecycleBinData,
  });
};
