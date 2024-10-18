import { HTTPError } from 'ky';
import { apiClient, returnErrorFromHTTPError } from '@/shared/api';
import type { GetPickResponseType } from '../../type';

export const getPick = async (pickId: number): Promise<GetPickResponseType> => {
  try {
    return await apiClient.get<GetPickResponseType>(`picks/${pickId}`).json();
  } catch (httpError) {
    if (httpError instanceof HTTPError) {
      const error = returnErrorFromHTTPError(httpError);
      throw error;
    }
    throw httpError;
  }
};
