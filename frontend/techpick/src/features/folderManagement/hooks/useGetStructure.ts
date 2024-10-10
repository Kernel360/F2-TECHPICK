import { useQuery } from '@tanstack/react-query';
import { getStructure } from '@/features/queryFunctions';

export const useGetStructure = () => {
  return useQuery({
    queryKey: ['getStructure'],
    queryFn: getStructure,
  });
};
