import { useQuery } from '@tanstack/react-query';
import { getPicksByParentId } from '@/components/nodeManagement/api/pick/pickQueryFunctions';
import { ApiPickData } from '@/types/ApiTypes';

export const useGetPicksByParentId = (parentId: string) => {
  return useQuery<ApiPickData[], Error>({
    queryKey: ['picksByParentId', parentId],
    queryFn: async () => await getPicksByParentId(parentId),
    enabled: !!parentId,
  });
};
