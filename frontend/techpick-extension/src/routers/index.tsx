import { createMemoryRouter } from 'react-router-dom';
import { BookmarkPage } from '../pages';

export const router = createMemoryRouter([
  {
    path: '/',
    element: <BookmarkPage />,
  },
  {
    path: '/login',
    element: <h1>Login Page</h1>,
  },
]);
