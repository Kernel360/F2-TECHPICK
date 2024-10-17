import { apiClient } from '@/shared/api/apiClient';

export const getUnclassifiedPicks = async () => {
  try {
    return await apiClient.get('picks?folderType=UNCLASSIFIED').json();
  } catch (error) {
    console.error('Error fetching unclassified picks:', error);
    throw error;
  }
};
