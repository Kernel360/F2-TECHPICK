import { createMemoryRouter } from 'react-router-dom';
import { LoginGuard } from '@/features/auth';
import { BookmarkPage, LoginPage } from '@/pages';

export const router = createMemoryRouter([
  {
    path: '/',
    element: (
      <LoginGuard>
        <BookmarkPage />
      </LoginGuard>
    ),
  },
  {
    path: '/login',
    element: <LoginPage />,
  },
]);
