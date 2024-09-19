'use client';

import { Toaster } from 'react-hot-toast';

function ClientProvider({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <>
      {children}
      <Toaster />
    </>
  );
}

export { ClientProvider };
