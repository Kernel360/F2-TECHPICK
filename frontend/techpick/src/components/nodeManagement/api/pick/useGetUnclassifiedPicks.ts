import { useQuery } from '@tanstack/react-query';
import { getUnclassifiedPicks } from '@/components/nodeManagement/api/pick/pickQueryFunctions';
import { ApiPickData } from '@/types/ApiTypes';

export const useGetUnclassifiedPicks = () => {
  return useQuery<ApiPickData[]>({
    queryKey: ['unclassifiedPicks'],
    queryFn: getUnclassifiedPicks,
  });
};
