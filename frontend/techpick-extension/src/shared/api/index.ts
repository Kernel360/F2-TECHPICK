import ky from 'ky';
import { API_URL } from '@/shared';

export let api = ky.create({
  prefixUrl: API_URL,
  headers: { 'content-type': 'application/json', credentials: 'include' },
});

export const addAccessTokenInAPIHeader = (accessTokenCookie: string) => {
  api = api.extend({
    headers: {
      Cookie: accessTokenCookie,
    },
  });
};
