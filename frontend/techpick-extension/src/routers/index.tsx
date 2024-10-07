import { createMemoryRouter } from 'react-router-dom';
import { BookmarkPage, LoginPage } from '@/pages';

export const router = createMemoryRouter([
  {
    path: '/',
    element: <BookmarkPage />,
  },
  {
    path: '/login',
    element: <LoginPage />,
  },
]);
