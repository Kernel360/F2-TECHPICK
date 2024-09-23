import { createMemoryRouter } from 'react-router-dom';
import { CurrentTabInfo } from '../components';

export const router = createMemoryRouter([
  {
    path: '/',
    element: (
      <>
        <h1>Main Page</h1>
        <CurrentTabInfo />
      </>
    ),
  },
  {
    path: '/login',
    element: <h1>Login Page</h1>,
  },
]);
