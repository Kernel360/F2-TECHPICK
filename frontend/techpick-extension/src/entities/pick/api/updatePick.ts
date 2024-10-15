import { api } from '@/shared';
import type { GetPickResponseType, UpdatePickRequest } from '../pick.type';

export const updatePick = async (pickInfo: UpdatePickRequest) => {
  const response = await api.put<GetPickResponseType>('picks', {
    json: pickInfo,
  });
  const data = await response.json();

  return data;
};
