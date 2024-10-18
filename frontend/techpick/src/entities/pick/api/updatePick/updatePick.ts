import { apiClient, returnErrorFromHTTPError } from '@/shared/api';
import { HTTPError } from 'ky';
import type { GetPickResponseType, UpdatePickRequestType } from '../../type';

export const updatePick = async (pickInfo: UpdatePickRequestType) => {
  try {
    const response = await apiClient.put<GetPickResponseType>('picks', {
      json: pickInfo,
    });
    const data = await response.json();

    return data;
  } catch (httpError) {
    if (httpError instanceof HTTPError) {
      const error = returnErrorFromHTTPError(httpError);
      throw error;
    }
    throw httpError;
  }
};
