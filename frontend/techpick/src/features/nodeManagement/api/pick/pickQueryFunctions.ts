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

export const putPickMove = async ({
  pickId,
  structure,
}: {
  pickId: string;
  structure: object;
}): Promise<void> => {
  return await apiClient
    .put(`structures/picks/${pickId}`, {
      json: structure,
    })
    .json();
};

export const getPicksByParentId = async (
  parentId: string
): Promise<ApiPickData[]> => {
  try {
    return await apiClient.get(`picks?parentId=${parentId}`).json();
  } catch (error) {
    console.error('Error fetching picks by parent ID:', error);
    throw error;
  }
};

export const deletePick = async ({
  pickId,
  structure,
}: {
  pickId: string;
  structure: object;
}): Promise<void> => {
  return await apiClient
    .delete(`structures/picks/${pickId}`, {
      json: structure,
    })
    .json();
};

export const getPickDetail = async (pickId: string): Promise<ApiPickData> => {
  try {
    return await apiClient.get(`/api/picks/${pickId}`).json();
  } catch (error) {
    console.error('Failed to fetch pick details:', error);
    throw error;
  }
};
