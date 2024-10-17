import { useQuery } from '@tanstack/react-query';
import { getUnclassifiedPicks } from '@/features/nodeManagement/api/pick/pickQueryFunctions';

export const useGetUnclassifiedPicks = () => {
  return useQuery({
    queryKey: ['unclassifiedPicks'],
    queryFn: getUnclassifiedPicks,
  });
};
