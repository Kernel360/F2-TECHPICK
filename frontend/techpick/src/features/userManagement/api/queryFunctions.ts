import { apiClient } from '@/shared/api/apiClient';

export const postLogout = async () => {
  return await apiClient.post('logout').json();
};
