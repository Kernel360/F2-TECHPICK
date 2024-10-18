import {
  ApiDefaultFolderIdData,
  ApiStructureData,
} from '@/shared/types/ApiTypes';
import { apiClient } from '@/shared/api/apiClient';

export const getRootAndRecycleBinData = async (): Promise<ApiStructureData> => {
  try {
    return await apiClient.get('structures').json<ApiStructureData>();
  } catch (error) {
    console.log('Error fetching structure data:', error);
    throw error;
  }
};

export const getFoldersIdData = async (): Promise<ApiDefaultFolderIdData> => {
  try {
    return await apiClient.get('folders').json<ApiDefaultFolderIdData>();
  } catch (error) {
    console.log('Error fetching folder ID data:', error);
    throw error;
  }
};

export const postFolder = async (
  folderName: string
): Promise<{
  id: number;
  name: string;
  parentFolderId: number;
  userId: number;
}> => {
  return await apiClient
    .post('folders', {
      json: { name: folderName },
    })
    .json();
};

export const putFolderMove = async ({
  folderId,
  structure,
}: {
  folderId: string;
  structure: object;
}): Promise<void> => {
  return await apiClient
    .put(`structures/folders/${folderId}`, {
      json: structure,
    })
    .json();
};

export const putFolderRename = async ({
  folderId,
  name,
}: {
  folderId: string;
  name: string;
}): Promise<void> => {
  return await apiClient
    .put(`folders/${folderId}`, {
      json: { name },
    })
    .json();
};

export const deleteFolder = async ({
  folderId,
  structure,
}: {
  folderId: string;
  structure: object;
}): Promise<void> => {
  return await apiClient
    .delete(`structures/folders/${folderId}`, {
      json: structure,
    })
    .json();
};
