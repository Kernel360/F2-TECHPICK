import { apiClient } from '@/shared/api';

type TagResponse = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
  userId: number;
}[];

export const getTagList = async () => {
  const response = await apiClient.get<TagResponse>('tag');
  const data = await response.json();

  return data;
};
