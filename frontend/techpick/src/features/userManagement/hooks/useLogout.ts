import { useMutation } from '@tanstack/react-query';
import { postLogout } from '@/features/userManagement/api/queryFunctions';

export const useLogout = () => {
  return useMutation({ mutationFn: postLogout });
};
