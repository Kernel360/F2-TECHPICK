'use client';

import { useThemeStore } from '@/shared';
import { lightTheme, darkTheme, commonThemeClass } from 'techpick-shared';

export const ThemeProvider = ({ children }: { children: React.ReactNode }) => {
  const { isDarkMode } = useThemeStore();

  const currentTheme = isDarkMode ? darkTheme : lightTheme;
  return (
    <div className={`${commonThemeClass} ${currentTheme}`}>{children}</div>
  );
};
