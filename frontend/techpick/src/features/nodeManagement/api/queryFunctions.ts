import ky from 'ky';
import { DefaultFolderIdData, StructureData } from '@/shared/types/ApiTypes';

export const apiClient = ky.create({
  credentials: 'include',
  prefixUrl: process.env.NEXT_PUBLIC_API,
  hooks: {},
});

export const getRootAndRecycleBinData = async (): Promise<StructureData> => {
  try {
    return await apiClient.get('structures').json<StructureData>();
  } catch (error) {
    console.log('Error fetching structure data:', error);
    throw error;
  }
};

export const getFoldersIdData = async (): Promise<DefaultFolderIdData> => {
  try {
    return await apiClient.get('folders').json<DefaultFolderIdData>();
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
