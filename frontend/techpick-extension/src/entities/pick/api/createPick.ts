import { api } from '@/shared';
import { CreatePickRequestType, CreatePickResponseType } from '../pick.type';

export const createPick = async (pickInfo: CreatePickRequestType) => {
  const response = await api.post<CreatePickResponseType>('picks', {
    json: pickInfo,
  });
  const data = await response.json();

  return data;
};
