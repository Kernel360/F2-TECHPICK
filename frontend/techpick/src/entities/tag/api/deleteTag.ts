import { api } from '@/shared/api';
import { TagType } from '../type';

export const deleteTag = async (deleteTagId: TagType['tagId']) => {
  await api.delete<never>(`tag/${deleteTagId}`);
};
