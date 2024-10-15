import ky from 'ky';

export const apiClient = ky.create({
  credentials: 'include',
  prefixUrl: process.env.NEXT_PUBLIC_API,
  hooks: {},
});

export const postLogout = async () => {
  return await apiClient.post('logout').json();
};
