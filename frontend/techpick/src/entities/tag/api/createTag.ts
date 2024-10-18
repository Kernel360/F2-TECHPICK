import { apiClient } from '@/shared/api';
import { CreateTagRequestType, CreateTagResponseType } from '../type';

export const createTag = async (createTag: CreateTagRequestType) => {
  const response = await apiClient.post<CreateTagResponseType>('tag', {
    body: JSON.stringify(createTag),
  });
  const data = await response.json();

  return data;
};
