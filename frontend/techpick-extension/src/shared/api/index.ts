import ky from 'ky';

export const api = ky.create({
  prefixUrl: import.meta.env.VITE_PUBLIC_API,
  headers: { 'content-type': 'application/json', credentials: 'include' },
});

export { handleHTTPError } from './error';
