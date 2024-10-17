import { apiClient } from '@/shared/api/apiClient';
import { ApiPickData, ApiPickRequestType } from '@/shared/types/ApiTypes';

export const getUnclassifiedPicks = async (): Promise<ApiPickData[]> => {
  try {
    return await apiClient.get('picks?folderType=UNCLASSIFIED').json();
  } catch (error) {
    console.error('Error fetching unclassified picks:', error);
    throw error;
  }
};

export const postPick = async ({
  memo,
  title,
  tagIdList,
  linkRequest,
}: ApiPickRequestType): Promise<ApiPickData> => {
  return await apiClient
    .post('picks', {
      json: { memo, title, tagIdList, linkRequest },
    })
    .json();
};
