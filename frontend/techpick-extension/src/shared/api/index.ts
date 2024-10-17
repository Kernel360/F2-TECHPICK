import ky from 'ky';

export const api = ky.create({
  credentials: 'include',
  prefixUrl: import.meta.env.VITE_PUBLIC_API,
  headers: { 'content-type': 'application/json' },
});

export { handleHTTPError, returnErrorFromHTTPError } from './error';
