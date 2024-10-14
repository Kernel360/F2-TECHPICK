import { api } from '@/shared';

// 추후 사용할 예정입니다.
// type TagResponse = components['schemas']['TagResponse'];

type TagResponse = {
  tagId: number;
  tagName: string;
  tagOrder: number;
  colorNumber: number;
  userId: number;
}[];

export const getTagList = async () => {
  const response = await api.get<TagResponse>('tag');
  const data = await response.json();

  return data;
};
