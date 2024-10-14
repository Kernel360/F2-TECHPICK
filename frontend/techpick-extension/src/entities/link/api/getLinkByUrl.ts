import { api } from '@/shared';
import { GetLinkResponseType } from '../link.type';

export const getLinkByUrl = async (url: string) => {
  const response = await api.get<GetLinkResponseType>(
    `links?url=${encodeURI(url)}`
  );
  const data = await response.json();

  return data;
};
