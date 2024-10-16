import { useQuery } from '@tanstack/react-query';
import { getRootAndRecycleBinData } from '@/features/nodeManagement/api/queryFunctions';

export const useGetRootAndRecycleBinData = () => {
  return useQuery({
    queryKey: ['rootAndRecycleBinData'],
    queryFn: getRootAndRecycleBinData,
  });
};
