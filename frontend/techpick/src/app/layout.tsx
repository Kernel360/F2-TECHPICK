import type { Metadata } from 'next';
import { Inter } from 'next/font/google';
import { ToastProvider, ThemeProvider } from '@/providers';
import '@/shared/themes/reset.css';
import { DndProviderWrapper } from '@/providers/DndProviderWrapper';
import { QueryProvider } from '@/providers/QueryProvider';

const inter = Inter({ subsets: ['latin'] });
export const metadata: Metadata = {
  title: 'Create Next App',
  description: 'Generated by create next app',
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body className={`${inter.className}`}>
        <ToastProvider>
          <ThemeProvider>
            <DndProviderWrapper>
              <QueryProvider>
                {children}
                <div id="portalContainer" />
              </QueryProvider>
            </DndProviderWrapper>
          </ThemeProvider>
        </ToastProvider>
      </body>
    </html>
  );
}
