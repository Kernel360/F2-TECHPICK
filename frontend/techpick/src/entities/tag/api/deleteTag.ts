import { apiClient } from '@/apis';
import { TagType } from '../type';

export const deleteTag = async (deleteTagId: TagType['tagId']) => {
  await apiClient.delete<never>(`tag/${deleteTagId}`);
};
