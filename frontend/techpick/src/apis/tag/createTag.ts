import { apiClient } from '@/apis';
import { CreateTagRequestType, CreateTagResponseType } from '@/types';

export const createTag = async (createTag: CreateTagRequestType) => {
  const response = await apiClient.post<CreateTagResponseType>('tag', {
    body: JSON.stringify(createTag),
  });
  const data = await response.json();

  return data;
};
