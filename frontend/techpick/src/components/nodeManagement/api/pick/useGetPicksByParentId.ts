import { getPicksByParentId } from '@/components/nodeManagement/api/pick/pickQueryFunctions';
import { useQuery } from '@tanstack/react-query';
import { ApiPickData } from '@/types/ApiTypes';

export const useGetPicksByParentId = (parentId: string) => {
  return useQuery<ApiPickData[], Error>({
    queryKey: ['picksByParentId', parentId],
    queryFn: async () => await getPicksByParentId(parentId),
    enabled: !!parentId,
  });
};
