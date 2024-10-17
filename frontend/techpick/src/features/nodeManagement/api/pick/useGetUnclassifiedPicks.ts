import { useQuery } from '@tanstack/react-query';
import { getUnclassifiedPicks } from '@/features/nodeManagement/api/pick/pickQueryFunctions';
import { ApiPickData } from '@/shared/types/ApiTypes';

export const useGetUnclassifiedPicks = () => {
  return useQuery<ApiPickData[]>({
    queryKey: ['unclassifiedPicks'],
    queryFn: getUnclassifiedPicks,
  });
};
