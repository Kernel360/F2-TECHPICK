import { api } from '@/shared';
import { TagUpdateType, UpdateTagResponseType } from '../type';

const findTargetTag = (
  tagList: UpdateTagResponseType,
  updateTag: TagUpdateType
) => {
  const targetTag = tagList.filter((tag) => tag.tagId === updateTag.tagId);
  return targetTag;
};

export const updateTag = async (updateTag: TagUpdateType) => {
  const response = await api.put<UpdateTagResponseType>('tag', {
    json: [updateTag],
  });
  const totalTagList = await response.json();
  const updatedTag = findTargetTag(totalTagList, updateTag);

  return updatedTag;
};
