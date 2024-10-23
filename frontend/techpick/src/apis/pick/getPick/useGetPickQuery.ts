import { useQuery } from '@tanstack/react-query';
import { getPick } from './getPick';
import type { GetPickResponseType } from '../pickApi.type';

// todo: suspense query로 바꾸기.
export const useGetPickQuery = (pickId: number) => {
  return useQuery<GetPickResponseType>({
    queryKey: ['pick', pickId],
    queryFn: () => {
      return getPick(pickId);
    },
  });
};
