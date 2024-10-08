import ky from 'ky';

export let api = ky.create({
  prefixUrl: import.meta.env.VITE_PUBLIC_API,
  headers: { 'content-type': 'application/json', credentials: 'include' },
});

export const addAccessTokenInAPIHeader = (accessTokenCookie: string) => {
  api = api.extend({
    headers: {
      Cookie: accessTokenCookie,
    },
  });
};
