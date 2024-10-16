import ky from 'ky';

export const apiClient = ky.create({
  credentials: 'include',
  prefixUrl: process.env.NEXT_PUBLIC_API,
  hooks: {},
});
