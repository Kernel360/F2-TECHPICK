import { api } from '@/shared';
import type { GetPickResponseType } from '../pick.type';

export const getPickByUrl = async (url: string) => {
  const response = await api.get<GetPickResponseType>(
    `picks/url?url=${encodeURIComponent(url)}`
  );

  const data = await response.json();

  return data;
};
